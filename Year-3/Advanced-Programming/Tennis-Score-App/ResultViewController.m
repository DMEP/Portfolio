//
//  ResultViewController.m
//  TennisScoreApp
//
//  Created by Daniel Elstob on 07/05/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import "ResultViewController.h"


@interface ResultViewController ()

@end

@implementation ResultViewController

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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)Twitter:(id)sender {
    //this twitter button code is from http://stackoverflow.com/questions/10563583/open-native-twitter-app-from-my-application-using-ibaction
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"twitter://user?screen_name=username"]];
}

- (IBAction)Print:(id)sender {
}
@end
