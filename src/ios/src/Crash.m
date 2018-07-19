#import "CrashManager.h"
#import "Crash.h"
#pragma mark - Crash
@interface Crash()

@end
@implementation Crash
static CrashManager *manager = nil;
static Crash *sdk = nil;

static NSString *appKey = @"xxxxxxx"; //appId
static NSString *secret = @"xxxxxxx"; //appSecret
static NSString *channel = @"base";//渠道标记
static NSString *appVersion = @"1.0"; //app版本

+(instancetype)Instance {
    return sdk;
}

- (void) pluginInitialize
{
    NSLog(@"Crash->%@", @"init");
    
    sdk = self;
    NSDictionary *tempInfoDict = [[NSBundle mainBundle] infoDictionary];
    NSDictionary *tempExecutable = [tempInfoDict valueForKey:@"GJPlugin"];
    
    appKey = [tempExecutable objectForKey:[@"CRASH_APP_KEY" lowercaseString]];

    secret = [tempExecutable objectForKey:[@"CRASH_APP_SECRET" lowercaseString]];

    manager = [[CrashManager alloc]init];
    [manager initService:appKey secret:secret channel:channel appVersion:appVersion];
}
@end
