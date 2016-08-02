% This function takes in a user input of columns 
% and creates a histogram
function [val] = histogram_analysis (data, t)
    % Close any open figures
    close all
   
   
    a = inputname(1);
    % Defines Bin specifications
    nbins = 20;
    
    % Figure and main title
    figure('Name','Histogram Analysis','NumberTitle','off')
    
    
    % Create histogram
    hist(data, nbins);
  
    
    % Create a vector of the values in each bin
    [val] = hist(data, nbins);
    title(t);
    ylabel(a);
    xlabel(nbins);
    
end

