//
//  GameLevelLayer.m
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//


#import "GameLevelLayer.h"
#import "Player.h"
#import "SimpleAudioEngine.h"
#import "level2.h"
#import "Won.h"

@interface GameLevelLayer()
{
    CCTMXTiledMap *map;
    Player * player;
    CCTMXLayer *foreground;
    CCTMXLayer *spikes;
    BOOL gameOver;
}

@end

@implementation GameLevelLayer

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	GameLevelLayer *layer = [GameLevelLayer node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

-(id) init
{
    [[SimpleAudioEngine sharedEngine] playBackgroundMusic:@"level1.mp3"]; // Reference Jake Gundersen (2012)( http://www.raywenderlich.com/15230/how-to-make-a-platform-game-like-super-mario-brothers-part-1 )
	// always call "super" init
	// Apple recommends to re-assign "self" with the "super's" return value
	if( (self=[super init]) ) {
        //init goes here
        CCLayerColor *blueSky = [[CCLayerColor alloc] initWithColor:ccc4(100, 100, 250, 255)];
        [self addChild:blueSky];
        
        map = [[CCTMXTiledMap alloc] initWithTMXFile:@"Level1.tmx"];
        foreground = [map layerNamed:@"Foreground"];
         spikes = [map layerNamed:@"Spike"];
        [self addChild:map];
        
        
        player = [[Player alloc] initWithFile:@"koalio_stand.png"];
        player.position = ccp(100, 50);
        [map addChild:player z:15];
        
        [self schedule:@selector(update:)];
	}
    self.isTouchEnabled = YES;
	return self;
}

-(void)update:(ccTime)dt {
    [player update:dt];
    
    [self handleHazardCollisions:player];
    [self checkForWin];
    [self checkForAndResolveCollisions:player];
    [self setViewpointCenter:player.position];
}
- (CGPoint)tileCoordForPosition:(CGPoint)position
{
    float x = floor(position.x / map.tileSize.width);
    float levelHeightInPixels = map.mapSize.height * map.tileSize.height;
    float y = floor((levelHeightInPixels - position.y) / map.tileSize.height);
    return ccp(x, y);
}

-(CGRect)tileRectFromTileCoords:(CGPoint)tileCoords
{
    float levelHeightInPixels = map.mapSize.height * map.tileSize.height;
    CGPoint origin = ccp(tileCoords.x * map.tileSize.width, levelHeightInPixels - ((tileCoords.y + 1) * map.tileSize.height));
    return CGRectMake(origin.x, origin.y, map.tileSize.width, map.tileSize.height);
}


// Reference Jake Gundersen (2012)( http://www.raywenderlich.com/15230/how-to-make-a-platform-game-like-super-mario-brothers-part-1 )

-(NSArray *)getSurroundingTilesAtPosition:(CGPoint)position forLayer:(CCTMXLayer *)layer {
    
    CGPoint plPos = [self tileCoordForPosition:position];
    
    NSMutableArray *gids = [NSMutableArray array];  //create a new array that you will return with all the tile information.
    
    for (int i = 0; i < 9; i++) {  // loop that will run nine times – because there are 9 possible spaces around player
        int c = i % 3;
        int r = (int)(i / 3);
        CGPoint tilePos = ccp(plPos.x + (c - 1), plPos.y + (r - 1));
        if (tilePos.y > (map.mapSize.height - 1)) {
            //fallen in a hole
            [self gameOver:0];
            return nil;
        }
        int tgid = [layer tileGIDAt:tilePos];  // This method will return the GID of the tile at a specific tile coordinate. If there’s no tile at that coordinate, it returns zero which will be used for holes
        
        CGRect tileRect = [self tileRectFromTileCoords:tilePos];  // use the helper method to calculate the Cocos2D world space coordinates for each tile’s CGRect
        
        
        
        //store all that information in an NSDictionary. The collection of dictionaries is put into the return array.
        NSDictionary *tileDict = [NSDictionary dictionaryWithObjectsAndKeys:
                                  [NSNumber numberWithInt:tgid], @"gid",
                                  [NSNumber numberWithFloat:tileRect.origin.x], @"x",
                                  [NSNumber numberWithFloat:tileRect.origin.y], @"y",
                                  [NSValue valueWithCGPoint:tilePos],@"tilePos",
                                  nil];
        [gids addObject:tileDict];
        
    }
    
    [gids removeObjectAtIndex:4];
    [gids insertObject:[gids objectAtIndex:2] atIndex:6];
    [gids removeObjectAtIndex:2];
    [gids exchangeObjectAtIndex:4 withObjectAtIndex:6];
    [gids exchangeObjectAtIndex:0 withObjectAtIndex:4]; 
    
   
    return (NSArray *)gids;
}

// Reference Jake Gundersen (2012)( http://www.raywenderlich.com/15230/how-to-make-a-platform-game-like-super-mario-brothers-part-1 )
-(void)checkForAndResolveCollisions:(Player *)p {
    
    NSArray *tiles = [self getSurroundingTilesAtPosition:p.position forLayer:foreground ]; 
    if (gameOver) {
        return;
    }
    p.onGround = NO; 
    
    for (NSDictionary *dic in tiles) {
        CGRect pRect = [p collisionBoundingBox]; 
        
        int gid = [[dic objectForKey:@"gid"] intValue]; 
        if (gid) {
            CGRect tileRect = CGRectMake([[dic objectForKey:@"x"] floatValue], [[dic objectForKey:@"y"] floatValue], map.tileSize.width, map.tileSize.height);
            if (CGRectIntersectsRect(pRect, tileRect)) {
                CGRect intersection = CGRectIntersection(pRect, tileRect);
                int tileIndx = [tiles indexOfObject:dic];
                
                if (tileIndx == 0) {
                    //tile is directly below player
                    p.desiredPosition = ccp(p.desiredPosition.x, p.desiredPosition.y + intersection.size.height);
                    p.velocity = ccp(p.velocity.x, 0.0); 
                    p.onGround = YES; 
                } else if (tileIndx == 1) {
                    //tile is directly above player
                    p.desiredPosition = ccp(p.desiredPosition.x, p.desiredPosition.y - intersection.size.height);
                    p.velocity = ccp(p.velocity.x, 0.0); 
                } else if (tileIndx == 2) {
                    //tile is left of player
                    p.desiredPosition = ccp(p.desiredPosition.x + intersection.size.width, p.desiredPosition.y);
                } else if (tileIndx == 3) {
                    //tile is right of player
                    p.desiredPosition = ccp(p.desiredPosition.x - intersection.size.width, p.desiredPosition.y);
                } else {
                    if (intersection.size.width > intersection.size.height) {
                        //tile is diagonal, but resolving collision vertially
                        p.velocity = ccp(p.velocity.x, 0.0); 
                        float resolutionHeight;
                        if (tileIndx > 5) {
                            resolutionHeight = intersection.size.height;
                            p.onGround = YES; 
                        } else {
                            resolutionHeight = -intersection.size.height;
                        }
                        p.desiredPosition = ccp(p.desiredPosition.x, p.desiredPosition.y + resolutionHeight);
                        
                    } else {
                        float resolutionWidth;
                        if (tileIndx == 6 || tileIndx == 4) {
                            resolutionWidth = intersection.size.width;
                        } else {
                            resolutionWidth = -intersection.size.width;
                        }
                        p.desiredPosition = ccp(p.desiredPosition.x + resolutionWidth, p.desiredPosition.y);
                    } 
                } 
            }
        } 
    }
    p.position = p.desiredPosition; 
}


- (void)ccTouchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    for (UITouch *t in touches) {
        CGPoint touchLocation = [self convertTouchToNodeSpace:t];
        if (touchLocation.x > 240) {
            player.Jump = YES;
        } else {
            player.Walk = YES;
        }
    }
}

- (void)ccTouchesMoved:(NSSet *)touches withEvent:(UIEvent *)event {
    for (UITouch *t in touches) {
        
        CGPoint touchLocation = [self convertTouchToNodeSpace:t];
        
        CGPoint previousTouchLocation = [t previousLocationInView:[t view]];
        CGSize screenSize = [[CCDirector sharedDirector] winSize];
        previousTouchLocation = ccp(previousTouchLocation.x, screenSize.height - previousTouchLocation.y);
        
        if (touchLocation.x > 240 && previousTouchLocation.x <= 240) {
            player.Walk = NO;
            player.Jump = YES;
        } else if (previousTouchLocation.x > 240 && touchLocation.x <=240) {
            player.Walk = YES;
            player.Jump = NO;
        }
    }
}

- (void)ccTouchesEnded:(NSSet *)touches withEvent:(UIEvent *)event {
    
    for (UITouch *t in touches) {
        CGPoint touchLocation = [self convertTouchToNodeSpace:t];
        if (touchLocation.x < 240) {
            player.Walk = NO;
        } else {
            player.Jump = NO;
        }
    }
}
-(void)setViewpointCenter:(CGPoint) position {
    
    CGSize winSize = [[CCDirector sharedDirector] winSize];
    
    int x = MAX(position.x, winSize.width / 2);
    int y = MAX(position.y, winSize.height / 2);
    x = MIN(x, (map.mapSize.width * map.tileSize.width)
            - winSize.width / 2);
    y = MIN(y, (map.mapSize.height * map.tileSize.height)
            - winSize.height/2);
    CGPoint actualPosition = ccp(x, y);
    
    CGPoint centerOfView = ccp(winSize.width/2, winSize.height/2);
    CGPoint viewPoint = ccpSub(centerOfView, actualPosition);
    map.position = viewPoint; 
}
-(void)handleHazardCollisions:(Player *)p {
    NSArray *tiles = [self getSurroundingTilesAtPosition:p.position forLayer:spikes ];
    for (NSDictionary *dic in tiles) {
        CGRect tileRect = CGRectMake([[dic objectForKey:@"x"] floatValue], [[dic objectForKey:@"y"] floatValue], map.tileSize.width, map.tileSize.height);
        CGRect pRect = [p collisionBoundingBox];
        
        if ([[dic objectForKey:@"gid"] intValue] && CGRectIntersectsRect(pRect, tileRect)) {
            [self gameOver:0];
        }
    }
}
-(void)gameOver:(BOOL)won {
	gameOver = YES;
	NSString *gameText;
    
	if (won) {
		gameText = @"You Won!";
          [[CCDirector sharedDirector] replaceScene:[Won scene]];
        
        
    } else {
		gameText = @"You have Died!";
            
    }
	
    
    CCLabelTTF *diedLabel = [[CCLabelTTF alloc] initWithString:gameText fontName:@"Marker Felt" fontSize:40];
    diedLabel.position = ccp(240, 200);
    CCMoveBy *slideIn = [[CCMoveBy alloc] initWithDuration:1.0 position:ccp(0, 250)];
    CCMenuItemImage *replay = [[CCMenuItemImage alloc] initWithNormalImage:@"replay.png" selectedImage:@"replay.png" disabledImage:@"replay.png" block:^(id sender) {
        [[CCDirector sharedDirector] replaceScene:[GameLevelLayer scene]];
    }];
    
    
    NSArray *menuItems = [NSArray arrayWithObject:replay];
    CCMenu *menu = [[CCMenu alloc] initWithArray:menuItems];
    menu.position = ccp(240, -100);
    
    [self addChild:menu];
    [self addChild:diedLabel];
    
    [menu runAction:slideIn];
    }
-(void)checkForWin {
    if (player.position.x > 3100.0) {
        [self gameOver:1];
    }
}
-(void)nextLevel:(id)sender
{
    [[CCDirector sharedDirector] replaceScene:[Level2 scene]];
}

@end
