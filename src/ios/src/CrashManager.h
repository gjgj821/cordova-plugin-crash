#import <Foundation/Foundation.h>

@interface CashManager : NSObject <Crash>

- (void)initService:(NSString *)appKey secret:(NSString *)secret channel:(NSString *)channel version:(NSString *)appVersion;
@end
