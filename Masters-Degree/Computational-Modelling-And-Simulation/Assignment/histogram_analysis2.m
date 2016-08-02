% This function takes in the variable from the workspace and creates  
% subplots of histograms of each columns variable for the specified nbins
function histogram_analysis2
    
    % Close any open figures
    close all

    % Link workspace variables
    c1 = evalin('base','c1');
    c2 = evalin('base','c2');
    c3 = evalin('base','c3');
    c4 = evalin('base','c4');
    c5 = evalin('base','c5');
    c6 = evalin('base','c6');
    c7 = evalin('base','c7');
    c8 = evalin('base','c8');
    c9 = evalin('base','c9');

    % Define Bin specifications
    nbins = 20;

    % Figure and main title
    figure('Name','Histogram Analysis 2','NumberTitle','off')
    suptitle('Histograms of all Attributes');
    
    % Subplots  -   Histograms
    subplot(3,3,1);hist(c1,nbins);ylabel('c1'),xlabel('bins');
    subplot(3,3,2);hist(c2,nbins);ylabel('c2'),xlabel('bins');
    subplot(3,3,3);hist(c3,nbins);ylabel('c3'),xlabel('bins');
    subplot(3,3,4);hist(c4,nbins);ylabel('c4'),xlabel('bins');
    subplot(3,3,5);hist(c5,nbins);ylabel('c5'),xlabel('bins');
    subplot(3,3,6);hist(c6,nbins);ylabel('c6'),xlabel('bins');
    subplot(3,3,7);hist(c7,nbins);ylabel('c7'),xlabel('bins');
    subplot(3,3,8);hist(c8,nbins);ylabel('c8'),xlabel('bins');
    subplot(3,3,9);hist(c9,nbins);ylabel('c9'),xlabel('bins');
    
end

