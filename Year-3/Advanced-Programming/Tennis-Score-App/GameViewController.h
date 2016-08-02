//
//  GameViewController.h
//  TennisScoreApp
//
//  Created by Daniel Elstob on 05/05/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AudioToolbox/AudioToolbox.h>

@interface GameViewController : UIViewController <UITextFieldDelegate>

@property SystemSoundID SoundID;

@property (strong, nonatomic) IBOutlet UITextField *Player1Name;
@property (strong, nonatomic) IBOutlet UITextField *Player2Name;

@property (strong, nonatomic) IBOutlet UILabel *Player1Label1;
@property (strong, nonatomic) IBOutlet UILabel *Player2Label1;
@property (strong, nonatomic) IBOutlet UILabel *Player1GameLabel;
@property (strong, nonatomic) IBOutlet UILabel *Player2GameLabel;
@property (strong, nonatomic) IBOutlet UILabel *Player1SetLabel;
@property (strong, nonatomic) IBOutlet UILabel *Player2SetLabel;
@property (strong, nonatomic) IBOutlet UILabel *setof;
@property (strong, nonatomic) IBOutlet UILabel *S1;
@property (strong, nonatomic) IBOutlet UILabel *S2;

- (IBAction)so3:(id)sender;
- (IBAction)so5:(id)sender;

- (IBAction)player1:(id)sender;
- (IBAction)player2:(id)sender;








@end
