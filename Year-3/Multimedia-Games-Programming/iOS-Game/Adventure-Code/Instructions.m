//
//  Instructions
//  Treasure Hunter
//
//  Created by Daniel Elstob on 6/02/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "GameLevelLayer.h"
#import "Instructions.h"

@implementation Instructions

+(CCScene *) scene{
    // 'scene' is an autorelease object.
    CCScene *scene = [CCScene node];
    
    // 'layer' is an autorelease object.
    Instructions *layer = [Instructions node];
    
    // add layer as a child to scene
    [scene addChild: layer];
    
    // return the scene
    return scene;
}

-(id) init{
    
    if( (self=[super init]) ) {
        
        
        
        // Sentence 1
        CCLabelTTF *label2 = [CCLabelTTF labelWithString:@"Character Foward : Touch Bottom Left Screen" fontName:@"Chalkduster" fontSize:20];
        //label.position = CCPositionTypeNormalized;
        //label.color = [CIColor redColor];
        label2.position = ccp(250 , 80); // Width , height
        [self addChild:label2];
        
        
        // Sentence 2
        CCLabelTTF *label3 = [CCLabelTTF labelWithString:@"Character Jump : Tap The Bottom Right" fontName:@"Chalkduster" fontSize:20];
        //label.position = CCPositionTypeNormalized;
        //label.color = [CIColor redColor];
        label3.position = ccp(250 , 50); // Width , height
        [self addChild:label3];
        
        
        // Sentence 3
        CCLabelTTF *label4 = [CCLabelTTF labelWithString:@"Hold Bottom Right For Higher Jump" fontName:@"Chalkduster" fontSize:20];
        label4.position = ccp(250 , 20); // Width , height
        [self addChild:label4];
        
        // Logo
        CCMenuItemImage *item1 = [CCMenuItemImage itemWithNormalImage:@"Chest.png" selectedImage:@"Chest.png" target:self selector:@selector(startGame:)];
        CCMenu *menu = [CCMenu menuWithItems:item1, nil];
        [self addChild:menu];
        
        return self;
        
        
    }
    return self;
}

-(void)startGame:(id)sender
{
    [[CCDirector sharedDirector] replaceScene:[GameLevelLayer scene]];
}
@end