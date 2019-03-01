//
//  WBFaceVerifyConst.h
//  Pods
//
//  Created by pp on 2017/7/31.
//
//
#import <UIKit/UIKit.h>
#ifndef WBFaceVerifyConst_h
#define WBFaceVerifyConst_h

UIKIT_EXTERN NSString *const WBCloudFaceVerifySDKVersion;// 当前版本3.3.0

/**
 具体的活体检测的类型:

 - WBFaceVerifyLivingType_Action:   动作活体
 - WBFaceVerifyLivingType_Number:   读4个数字活体
 - WBFaceVerifyLivingType_Light:    光线活体
 */
typedef NS_ENUM(NSInteger,WBFaceVerifyLivingType){
    WBFaceVerifyLivingType_Action,// 老版本的 - WBFaceVerifyLivingType_Middle
    WBFaceVerifyLivingType_Number,// 老版本的 - WBFaceVerifyLivingType_Advanced
    WBFaceVerifyLivingType_Light,
};


/**
 具体的服务类型:

 - WBFaceVerifyCompareType_None:        进行活体检测服务, 不进行人脸比对服务
 - WBFaceVerifyCompareType_SourceImage: 进行活体检测服务, 使用人脸比对服务: 对比源有合作方传入SDK中
 - WBFaceVerifyCompareType_IDCard:      进行活体检测服务, 使用人脸比对服务: 对比源由(身份证+姓名)公安照片进行比对
 */
typedef NS_ENUM(NSInteger,WBFaceVerifyCompareType){
    WBFaceVerifyCompareType_None,
    WBFaceVerifyCompareType_SourceImage,
    WBFaceVerifyCompareType_IDCard,
};


/**
 SDK使用的主题风格

 - WBFaceVerifyThemeDarkness: 暗黑色系主题(当前只支持暗黑色系)
 - WBFaceVerifyThemeLightness: 明亮色系主题
 */
typedef NS_ENUM(NSInteger, WBFaceVerifyTheme) {
    WBFaceVerifyThemeDarkness,
    WBFaceVerifyThemeLightness,
};

#endif /* WBFaceVerifyConst_h */
