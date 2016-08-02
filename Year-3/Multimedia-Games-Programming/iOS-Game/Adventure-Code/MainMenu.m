//
//  MainMenu.m
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "MainMenu.h"
#import "IntroScene.h"
#import "Instructions.h"

@implementation MainMenu

+(CCScene *) scene{
    // 'scene' is an autorelease object.
    CCScene *scene = [CCScene node];
    
    // 'layer' is an autorelease object.
    MainMenu *layer = [MainMenu node];
    
    // add layer as a child to scene
    [scene addChild: layer];
    
    // return the scene
    return scene;
}

-(id) init{
    
    if( (self=[super init]) ) {
        
        // Logo
        CCMenuItemImage *item1 = [CCMenuItemImage itemWithNormalImage:@"Chest.png" selectedImage:@"Chest.png"  target:self selector:@selector(startGame:)];
        CCMenu *menu = [CCMenu menuWithItems:item1, nil];
        [self addChild:menu];
        
        // Title
        CCLabelTTF *label2 = [CCLabelTTF labelWithString:@"Adventurer" fontName:@"Chalkduster" fontSize:30];
        label2.position = ccp(250 , 100); // Middle of screen
        [self addChild:label2];
        
        
        return self;
        
        
    }
    return self;
}

-(void)startGame:(id)sender
{
    [[CCDirector sharedDirector] replaceScene:[IntroScene scene]];
}

@end