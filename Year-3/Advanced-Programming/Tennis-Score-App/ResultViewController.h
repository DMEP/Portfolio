//
//  ResultViewController.h
//  TennisScoreApp
//
//  Created by Daniel Elstob on 07/05/2014.
//  Copyright (c) 2014 Daniel Elstob. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ResultViewController : UIViewController
@property (strong, nonatomic) IBOutlet UILabel *RPlayerLabel;

- (IBAction)Twitter:(id)sender;
- (IBAction)Print:(id)sender;
@end
