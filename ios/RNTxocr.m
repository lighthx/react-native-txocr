
#import "RNTxocr.h"

@implementation RNTxocr {
    bool hasListeners;
}
-(void)startObserving {
    hasListeners = YES;
}

// Will be called when this module's last listener is removed, or on dealloc.
-(void)stopObserving {
    hasListeners = NO;
}
- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
-(WBFaceVerifySDKConfig *)getSDKSettings{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    WBFaceVerifySDKConfig *config = [WBFaceVerifySDKConfig sdkConfig];
    BOOL recordVideo = ![defaults boolForKey:@"settingHome_record_video"];
    config.recordVideo = recordVideo;
    config.theme = [defaults boolForKey:@"settingHome_light_theme"] ? WBFaceVerifyThemeLightness: WBFaceVerifyThemeDarkness;
    config.showSuccessPage = [defaults boolForKey:@"settingHome_success_page"];
    config.showFailurePage = [defaults boolForKey:@"settingHome_failure_page"];
    return  config;
}
RCT_EXPORT_MODULE()
RCT_REMAP_METHOD(facecheck ,userid:(NSString *)userid
                 nonce:(NSString *)nonce
                 sign:(NSString *)sign
                 appid:(NSString *)appid
                 orderNo:(NSString *)orderNo
                 apiVersion:(NSString *)apiVersion
                 licence:(NSString *)licence
                 userName:(NSString *)userName
                 idCard:(NSString *)idCard
                 ip:(NSString *)ip
                 findEventsWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject){
    WBFaceUserInfo *userInfo = [[WBFaceUserInfo alloc] init];
    userInfo.idType = @"01";
    userInfo.name = userName;
    userInfo.idNo = idCard;
    
    WBFaceVerifySDKConfig *config = [self getSDKSettings];

    [[WBFaceVerifyCustomerService sharedInstance] startLoginLiveCheckAndCompareServiceWithUserid:userid nonce:nonce sign:sign appid:appid orderNo:orderNo apiVersion:apiVersion licence:licence faceverifyType:WBFaceVerifyLivingType_Light userInfo:userInfo configure:config success:^{
        printf("ssssssssuccess");
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(weakNotification:) name:WBFaceVerifyCustomerServiceDidFinishedNotification object:nil];
        resolve(@"success");
    } failure:^(WBFaceError * _Nonnull error) {

        reject(error.desc,error.desc,nil);
    }];
    
}
-(void)weakNotification:(WBFaceVerifyResult *)faceVerifyResult{
        printf("finish");
    if(hasListeners){
        BOOL isSucess= faceVerifyResult.isSuccess;
        NSString *similarity=faceVerifyResult.similarity;
        NSString *liveRate=faceVerifyResult.liveRate;
        NSString *image=faceVerifyResult.userImageString;
        NSDictionary *map=@{
                            @"isSucess":@(isSucess),
                            @"similarity":similarity,
                            @"liveRate":liveRate,
                            @"image":image,
                            };
        [self sendEventWithName:@"finish" body:map];
        NSNotificationCenter *nc = [NSNotificationCenter defaultCenter];
        [nc removeObserver:self];
    }
}
- (NSArray<NSString *> *)supportedEvents
{
    return @[@"finish"];
}
@end
  
