#import <Foundation/Foundation.h>

@interface CrashManager : NSObject

- (void)initService:(NSString *)appKey secret:(NSString *)secret channel:(NSString *)channel appVersion:(NSString *)appVersion;
@end
