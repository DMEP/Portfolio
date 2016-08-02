//
//  Player.h
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "cocos2d.h"

@interface Player : CCSprite

@property (nonatomic, assign) CGPoint velocity;
@property (nonatomic, assign) CGPoint desiredPosition;
@property (nonatomic, assign) BOOL onGround;
@property (nonatomic, assign) BOOL Walk;
@property (nonatomic, assign) BOOL Jump;

-(void)update:(ccTime)dt;
-(CGRect)collisionBoundingBox;
@end