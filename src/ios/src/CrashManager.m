#import <AliHAAdapter4Cloud/AliHAAdapter.h>
#import <TBCrashReporter/TBCrashReporter.h>
#import <UT/UTAnalytics.h>
#import "CrashManager.h"

@interface CrashManager()

@end
@implementation CrashManager

- (instancetype)init{
  self = [super init];
  return self;
}

- (void)initService:(NSString *)appKey secret:(NSString *)secret channel:(NSString *)channel appVersion:(NSString *)appVersion{
  [[UTAnalytics getInstance] setAppKey:appKey secret:secret];
  [[UTAnalytics getInstance] setChannel:channel];
  [[UTAnalytics getInstance] setAppVersion:appVersion];id<AliHAPluginProtocol> crashPlugin = [TBCrashReporter sharedReporter];NSArray<id<AliHAPluginProtocol>> *plugins = @[crashPlugin];[AliHAAdapter initWithAppKey:appKey appVersion:appVersion channel:channel plugins:plugins nick:nil];
}
@end
