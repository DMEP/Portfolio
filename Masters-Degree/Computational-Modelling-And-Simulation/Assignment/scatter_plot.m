% This function takes in 2 user inputs of columns and creates a 
% scatter plot of the two inputs, gives the figure a title and labels axis
function scatter_plot (x,y,t)
    % Close any open figures
    close all
    
    % Link attributes with local variable
    a = inputname(1);
    b = inputname(2);
    
    % Figure and main title
    figure('Name','Scatter Plot','NumberTitle','off')
    
    % Create scater plot of the
    scatter(x,y); 
    
    % Create title and axis labels
    title(t); 
    xlabel(a); 
    ylabel(b);    
end


