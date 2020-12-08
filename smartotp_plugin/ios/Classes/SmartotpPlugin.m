#import "SmartotpPlugin.h"
#if __has_include(<smartotp_plugin/smartotp_plugin-Swift.h>)
#import <smartotp_plugin/smartotp_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "smartotp_plugin-Swift.h"
#endif

@implementation SmartotpPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSmartotpPlugin registerWithRegistrar:registrar];
}
@end
