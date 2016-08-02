//
//  GameViewController.m
//  TennisScoreApp
//
//  Created by Daniel Elstob on 05/05/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "GameViewController.h"


@interface GameViewController ()

@end

@implementation GameViewController

@synthesize Player1Name, Player2Name;
@synthesize Player1Label1, Player2Label1;
@synthesize Player1GameLabel, Player2GameLabel;
@synthesize Player1SetLabel, Player2SetLabel;
@synthesize setof;
@synthesize S1, S2;

int player1Point, player2Point;
int score1 = 1, score2 = 1;


-(void)player1Point{
    player1Point = 0;
    
}

-(void)player2Point{
    player2Point = 0;
    
}


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    //sound file  modified from http://raws.adc.rmit.edu.au/~e46191/Xcode/sound.html
//    NSURL *buttonURL = [NSURL fileURLWithPath:[[NSBundle mainBundle] pathForResource:@"jump" ofType:@"band"]];
//    AudioServicesCreateSystemSoundID((__bridge CFURLRef) buttonURL, &_SoundID);
    
    
    // this is the keybaord dismiss from http://stackoverflow.com/questions/5306240/iphone-dismiss-keyboard-when-touching-outside-of-textfield
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)so3:(id)sender {
    NSString * input1 = Player1Name.text;
    Player1Label1.text = input1;
    NSString * input2 = Player2Name.text;
    Player2Label1.text = input2;
    setof.text = @"3";
}

- (IBAction)so5:(id)sender {
    NSString * input1 = Player1Name.text;
    Player1Label1.text = input1;
    NSString * input2 = Player2Name.text;
    Player2Label1.text = input2;
    setof.text = @"5";
}

- (IBAction)player1:(id)sender {
    player1Point += 1;
    if(player1Point== 1 || player1Point == 3){
        S1.text = @"S";
        S1.textColor = [UIColor redColor];
        S2.text = @"-";
        S2.textColor = [UIColor blackColor];
    }
    if(player1Point== 2 || player1Point == 4){
        S1.text = @"-";
        S1.textColor = [UIColor blackColor];
        S2.text = @"S";
        S2.textColor = [UIColor redColor];
    }
        
   if(player1Point==1){
        Player1GameLabel.text = @"15";
    }
    else if (player1Point==2){
        Player1GameLabel.text = @"30";
    }
    else if (player1Point==3){
        Player1GameLabel.text = @"40";
    }
    else if (player1Point>=4 && player1Point >= player2Point +1 ){
        // Check if p2 score is A & if p1 is 40
        if (player2Point==3 && [Player1GameLabel.text isEqualToString:@"40"] && [Player2GameLabel.text  isEqualToString:@"A"]) {
            Player2GameLabel.text = @"40";
            player1Point--;
        }
        // else if p2 is not A, & p1 is 40, make p1 A
        else if (player2Point==3 && ![Player2GameLabel.text isEqualToString: @"A"])
        {
            Player1GameLabel.text = @"A";
            player2Point--;
        }
        // else reset score
        else{
            Player1GameLabel.text = @"0";
            Player2GameLabel.text = @"0";
            Player1SetLabel.text = [NSString stringWithFormat:@"%i", score1++];
            player1Point = 0;
            player2Point = 0;
            
        }
        // Sets win 3
        if ([Player1SetLabel.text isEqualToString:@"2"] && [setof.text isEqualToString:@"3"])
        {
            UIAlertView *winAlert2 = [[UIAlertView alloc] initWithTitle:@"Winner !" message: [NSString stringWithFormat: @"Player 1 Won !"]                                                        delegate:nil cancelButtonTitle: @"Play Again?" otherButtonTitles:nil];
            
            [winAlert2 show];
            Player1SetLabel.text = @"0";
            Player2SetLabel.text = @"0";
            score1 = 1;
            score2 = 1;
            
        }
        // Sets win 5
        if ([Player1SetLabel.text isEqualToString:@"3"] && [setof.text isEqualToString:@"5"])
        {
            UIAlertView *winAlert1 = [[UIAlertView alloc] initWithTitle:@"Winner !" message: [NSString stringWithFormat: @"Player 1 Won !"]                                                        delegate:nil cancelButtonTitle: @"Play Again?" otherButtonTitles:nil];
            
            [winAlert1 show];
            Player1SetLabel.text = @"0";
            Player2SetLabel.text = @"0";
            score1 = 1;
            score2 = 1;
            }}}

- (IBAction)player2:(id)sender {
    player2Point += 1;
    if(player2Point== 1 || player2Point == 3){
        S1.text = @"S";
        S1.textColor = [UIColor redColor];
        S2.text = @"-";
        S2.textColor = [UIColor blackColor];
//        AudioServicesPlaySystemSound(_SoundID);
    }
    if(player2Point== 2 || player2Point == 4){
        S1.text = @"-";
        S1.textColor = [UIColor blackColor];
        S2.text = @"S";
        S2.textColor = [UIColor redColor];
    }
    
    if(player2Point==1){
        Player2GameLabel.text = @"15";
    }
    else if (player2Point==2){
        Player2GameLabel.text = @"30";
    }
    else if (player2Point==3){
        Player2GameLabel.text = @"40";
    }
    else if (player2Point>=4 && player2Point >= player1Point +1 ){
        // Check if p1 score is A & if p2 is 40
        if (player1Point==3 && [Player2GameLabel.text isEqualToString:@"40"] && [Player1GameLabel.text  isEqualToString:@"A"]) {
            Player1GameLabel.text = @"40";
            player2Point--;
        }
        // else if p1 is not A, & p2 is 40, make p2 A
        else if (player1Point==3 && ![Player1GameLabel.text isEqualToString: @"A"])
        {
            Player2GameLabel.text = @"A";
            player1Point--;
        }
        // else reset score
        else{
            Player1GameLabel.text = @"0";
            Player2GameLabel.text = @"0";
            Player2SetLabel.text = [NSString stringWithFormat:@"%i", score2++];
            player1Point = 0;
            player2Point = 0;
            
        }
        // Sets win 3
        if ([Player2SetLabel.text isEqualToString:@"2"] && [setof.text isEqualToString:@"3"])
        {
            UIAlertView *winAlert2 = [[UIAlertView alloc] initWithTitle:@"Winner !" message: [NSString stringWithFormat: @"Player 2 Won !"]                                                        delegate:nil cancelButtonTitle: @"Play Again?" otherButtonTitles:nil];
            
            [winAlert2 show];
            Player1SetLabel.text = @"0";
            Player2SetLabel.text = @"0";
            score1 = 1;
            score2 = 1;
            
        }
        // Sets win 5
        if ([Player2SetLabel.text isEqualToString:@"3"] && [setof.text isEqualToString:@"5"])
        {
            UIAlertView *winAlert2 = [[UIAlertView alloc] initWithTitle:@"Winner !" message: [NSString stringWithFormat: @"Player 2 Won !"]                                                        delegate:nil cancelButtonTitle: @"Play Again?" otherButtonTitles: nil];
            
            [winAlert2 show];
         
            Player1SetLabel.text = @"0";
            Player2SetLabel.text = @"0";
            score1 = 1;
            score2 = 1;
            
        }
    };
}

// this is the keybaord dismiss slightly modified from http://stackoverflow.com/questions/5306240/iphone-dismiss-keyboard-when-touching-outside-of-textfield

-(void)dismissKeyboard {
    [Player1Name resignFirstResponder];
    [Player2Name resignFirstResponder];
}



@end