//
//  FinalWin.h
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "Won.h"
#import "level2.h"
#import "MainMenu.h"

@implementation FinalWin : CCLayer

+(CCScene *) scene{
    // 'scene' is an autorelease object.
    CCScene *scene = [CCScene node];
    
    // 'layer' is an autorelease object.
    FinalWin *layer = [FinalWin node];
    
    // add layer as a child to scene
    [scene addChild: layer];
    
    // return the scene
    return scene;
}

-(id) init{
    
    if( (self=[super init]) ) {
        
        
        CCLabelTTF *winLabel = [CCLabelTTF labelWithString:@"Well Done! Back  to Main Menu" fontName:@"Marker Felt" fontSize:20];
        CCMenuItemLabel *startMenu = [CCMenuItemLabel itemWithLabel:winLabel block:^(id sender) {
            [[CCDirector sharedDirector] replaceScene:[MainMenu scene]];
        }];
        startMenu.position = ccp(200,200);
        CCMenu *Menu = [CCMenu menuWithItems:startMenu, nil];
        [self addChild:Menu];
        
        
    }
    return self;
}

@end
