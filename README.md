
# react-native-txocr

## Getting started

`$ npm install react-native-txocr --save`

### Mostly automatic installation

`$ react-native link react-native-txocr`

### Manual installation

##注意
1.github单个上传文件不能超过100M，请到react-native-txocr/ios/WBCloudReflectionFaceVerify/sdks下 将rapidnet_ios.framework.zip解压出来，或者自行下载sdk替换,否则IOS编译时会报错

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-txocr` and add `RNTxocr.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNTxocr.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNTxocrPackage;` to the imports at the top of the file
  - Add `new RNTxocrPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-txocr'
  	project(':react-native-txocr').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-txocr/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-txocr')
  	```
4. 

## additional installation(require)

#### ios
1. drag  `react-native-txocr/ios/WBCloudReflectionFaceVerify/bundles/*` and `react-native-txocr/ios/WBCloudReflectionFaceVerify/sdks/*` all sdk and bundles to your project in xcode 
2. open `Build Phases`➜`Link Binary With Libraries` add `UIKit.framework`
                                                          `AVFoundation.framework`
                                                         `MobileCoreServices.framework`
                                                          `CoreVideo.framework`
                                                          `Accelerate.framework`
                                                          `Security.framework`
                                                          `SystemConfiguration.framework`
                                                          `CoreMedia.framework`
                                                          `AudioToolbox.framework`
                                                          `CoreTelephony.framework`
                                                          `ImageIO.framework`
                                                          `WebKit.framework`
                                                         `libc++.tbd` and `react-native-txocr/ios/WBCloudReflectionFaceVerify/sdks/*` all sdk;
3. Open `Build Settings` ➜ `Framework Search Paths` add `$(SRCROOT)/../node_modules/react-native-txocr/ios/WBCloudReflectionFaceVerify/sdks`


#### android
1. Open `android/build.gradle`
```
...
allprojects {
    repositories {
        .....
        flatDir {
            dirs project(':react-native-txocr').file('txocrlib')
        }  //ADD THIS
        ....
    }
}
...
```

## Usage


```typescript
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

```
  