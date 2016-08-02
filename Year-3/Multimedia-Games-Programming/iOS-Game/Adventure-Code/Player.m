//
//  Player.m
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "Player.h"
#import "SimpleAudioEngine.h"

@implementation Player

@synthesize velocity = _velocity;
@synthesize desiredPosition = _desiredPosition;
@synthesize onGround = _onGround;
@synthesize Walk = _Walk, Jump = _Jump;


-(id)initWithFile:(NSString *)filename
{
    if (self = [super initWithFile:filename]) {
        self.velocity = ccp(0.0, 0.0);
    }
    return self;
}

-(void)update:(ccTime)dt {
    CGPoint gravity = ccp(0.0, -450.0);
    CGPoint gravityStep = ccpMult(gravity, dt);
    
    CGPoint forwardMove = ccp(800.0, 0.0);
    CGPoint forwardStep = ccpMult(forwardMove, dt);
    
    self.velocity = ccpAdd(self.velocity, gravityStep);
    self.velocity = ccp(self.velocity.x * 0.90, self.velocity.y);
    
    CGPoint jumpForce = ccp(0.0, 310.0);
    float jumpCutoff = 150.0;
    
    if (self.Jump && self.onGround) {
        self.velocity = ccpAdd(self.velocity, jumpForce);
        [[SimpleAudioEngine sharedEngine] playEffect:@"jump.wav"];  // Audio Reference Jake Gundersen (2012)( http://www.raywenderlich.com/15230/how-to-make-a-platform-game-like-super-mario-brothers-part-1 )
    } else if (!self.Jump && self.velocity.y > jumpCutoff) {
        self.velocity = ccp(self.velocity.x, jumpCutoff);
    }
    
    if (self.Walk) {
        self.velocity = ccpAdd(self.velocity, forwardStep);
    } 
    
    CGPoint minMovement = ccp(0.0, -450.0);
    CGPoint maxMovement = ccp(120.0, 250.0);
    self.velocity = ccpClamp(self.velocity, minMovement, maxMovement); 
    
    CGPoint stepVelocity = ccpMult(self.velocity, dt);
    
    self.desiredPosition = ccpAdd(self.position, stepVelocity);
}
-(CGRect)collisionBoundingBox {
    CGRect collisionBox = CGRectInset(self.boundingBox, 3, 0);
    CGPoint diff = ccpSub(self.desiredPosition, self.position);
    CGRect returnBoundingBox = CGRectOffset(collisionBox, diff.x, diff.y);
    return returnBoundingBox;
}
@end