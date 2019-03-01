import {NativeModules, Platform, NativeEventEmitter} from 'react-native';

const {RNTxocr} = NativeModules;
const ocr = async (params) => {

    const promise = new Promise(async (resolve, reject) => {
        try {
            const {userId, nonceStr, sign, appId, orderNo, version, license, name, idCard, ip}=params
            if(!userId){
                throw Error("not has userId")
            }
            if(!nonceStr){
                throw Error("not has nonceStr")
            }
            if(!sign){
                throw Error("not has sign")
            }
            if(!appId){
                throw Error("not has appId")
            }
            if(!orderNo){
                throw Error("not has orderNo")
            }
            if(!version){
                throw Error("not has version")
            }
            if(!license){
                throw Error("not has license")
            }
            if(!name){
                throw Error("not has name")
            }
            if(!idCard){
                throw Error("not has idCard")
            }
            if(!ip){
                throw Error("not has ip")
            }
            const data = await RNTxocr.facecheck(userId, nonceStr, sign, appId, orderNo, version, license, name, idCard, ip);
            if (Platform.OS === "android") {
                resolve(data)
            } else {
                const OCREmitter = new NativeEventEmitter(RNTxocr);
                const listener = OCREmitter.addListener("finish", (v) => {
                    resolve(v);
                    listener.remove()
                })
            }
        } catch (e) {
            console.warn(`txocr error: ${e}`)
        }

    })
    return promise;

}
export default ocr;
