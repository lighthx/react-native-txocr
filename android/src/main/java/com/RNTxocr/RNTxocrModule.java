
package com.RNTxocr;

import android.os.Bundle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.webank.facelight.contants.WbCloudFaceContant;
import com.webank.facelight.contants.WbFaceError;
import com.webank.facelight.contants.WbFaceVerifyResult;
import com.webank.facelight.listerners.WbCloudFaceVeirfyLoginListner;
import com.webank.facelight.listerners.WbCloudFaceVeirfyResultListener;
import com.webank.facelight.tools.WbCloudFaceVerifySdk;
import com.webank.facelight.ui.FaceVerifyStatus;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
public class RNTxocrModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNTxocrModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNTxocr";
    }

    @ReactMethod
    public void facecheck(
            String userid,
            String nonce,
            String sign,
            String appid,
            String orderNo,
            String apiVersion,
            String licence,
            String userName,
            String idCard,
            String ip,
            final Promise promise
    ) {
        try {
            Bundle data = new Bundle();
            WbCloudFaceVerifySdk.InputData inputData = new WbCloudFaceVerifySdk.InputData(
                    userName,
                    "01",
                    idCard,
                    orderNo,
                    ip,
                    "lgt=22.5044;lat=113.9537",
                    appid,
                    apiVersion,
                    nonce,
                    userid,
                    sign,
                    FaceVerifyStatus.Mode.REFLECTION,
                    licence
            );
            data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
            data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, false);
            data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, false);

            WbCloudFaceVerifySdk.getInstance().init(
                    reactContext,
                    data,
                    new WbCloudFaceVeirfyLoginListner() {
                        @Override
                        public void onLoginSuccess() {
                            WbCloudFaceVerifySdk.getInstance().startWbFaceVeirifySdk(reactContext, new WbCloudFaceVeirfyResultListener() {
                                @Override
                                public void onFinish(WbFaceVerifyResult wbFaceVerifyResult) {
                                    boolean isSuccess = wbFaceVerifyResult.isSuccess();
                                    String similarity = wbFaceVerifyResult.getSimilarity();
                                    String liveRate = wbFaceVerifyResult.getLiveRate();
                                    String image = wbFaceVerifyResult.getUserImageString();
                                    WritableMap map=Arguments.createMap() ;
                                    map.putBoolean("isSuccess",isSuccess);
                                    map.putString("similarity",similarity);
                                    map.putString("liveRate",liveRate);
                                    map.putString("image",image);
                                    promise.resolve(map);
                                }
                            });
                        }

                        @Override
                        public void onLoginFailed(WbFaceError wbFaceError) {
                            promise.reject(wbFaceError.getDesc(), wbFaceError.getDesc(), null);
                        }
                    }
            );
        } catch (IllegalViewOperationException e) {

        }
    }
}