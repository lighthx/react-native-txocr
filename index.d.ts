interface Params {
    userId:string,
    nonceStr:string,
    sign:string,
    appId:string,
    orderNo:string,
    version:string,
    license:string,
    name:string,
    idCard:string,
    ip:string,
}
interface Result {
    isSuccess:boolean,
    similarity:string,
    liveRate:string,
    image:string,
}
export default function ocr(params:Params): Promise<Result>