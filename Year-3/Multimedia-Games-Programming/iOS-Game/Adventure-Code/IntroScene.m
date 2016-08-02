//
//  IntroScene.m
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "IntroScene.h"
#import "Instructions.h"

@implementation IntroScene

+(CCScene *) scene{
    // 'scene' is an autorelease object.
    CCScene *scene = [CCScene node];
    
    // 'layer' is an autorelease object.
    IntroScene *layer = [IntroScene node];
    
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
        
        // Sentence 1
        CCLabelTTF *label2 = [CCLabelTTF labelWithString:@"You Are A Adventurer, " fontName:@"Chalkduster" fontSize:20];
        label2.position = ccp(250 , 100); // Width , height
        [self addChild:label2];
        
        
        // Sentnce 2
        CCLabelTTF *label3 = [CCLabelTTF labelWithString:@"Reach The End Of The Level" fontName:@"Chalkduster" fontSize:20];
        label3.position = ccp(250 , 75); // Width , height
        [self addChild:label3];
        
        return self;
        
        
    }
    return self;
}

-(void)startGame:(id)sender
{
    [[CCDirector sharedDirector] replaceScene:[Instructions scene]];
}
@end