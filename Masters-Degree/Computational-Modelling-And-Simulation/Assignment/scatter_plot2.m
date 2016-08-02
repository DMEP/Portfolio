% This function takes in a user input of columns and creates a 
% subplot of scatterplots of the input against all other variables
function scatter_plot2 (data)
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

    % Figure and main title
    figure('Name','Scatter Plot 2','NumberTitle','off')
        
    % If Else for creating figures based on user input
    if data == c1
    % Subplots  -  Scatter Plot
    suptitle('Attribute 1 vs. 2:8');
    subplot(3,3,1);scatter(c1,c2);xlabel('c1'),ylabel('c2');
    subplot(3,3,2);scatter(c1,c3);xlabel('c1'),ylabel('c3');
    subplot(3,3,3);scatter(c1,c4);xlabel('c1'),ylabel('c4');
    subplot(3,3,4);scatter(c1,c5);xlabel('c1'),ylabel('c5');
    subplot(3,3,5);scatter(c1,c6);xlabel('c1'),ylabel('c6');
    subplot(3,3,6);scatter(c1,c7);xlabel('c1'),ylabel('c7');
    subplot(3,3,7);scatter(c1,c8);xlabel('c1'),ylabel('c8');

    elseif data == c2 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 2 vs. 3:8');
    subplot(3,3,1);scatter(c2,c3);xlabel('c2'),ylabel('c3');
    subplot(3,3,2);scatter(c2,c4);xlabel('c2'),ylabel('c4');
    subplot(3,3,3);scatter(c2,c5);xlabel('c2'),ylabel('c5');
    subplot(3,3,4);scatter(c2,c6);xlabel('c2'),ylabel('c6');
    subplot(3,3,5);scatter(c2,c7);xlabel('c2'),ylabel('c7');
    subplot(3,3,6);scatter(c2,c8);xlabel('c2'),ylabel('c8');
    
    elseif data == c3 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 3 vs. 4:8');
    subplot(2,3,1);scatter(c3,c4);xlabel('c3'),ylabel('c4');
    subplot(2,3,2);scatter(c3,c5);xlabel('c3'),ylabel('c5');
    subplot(2,3,3);scatter(c3,c6);xlabel('c3'),ylabel('c6');
    subplot(2,3,4);scatter(c3,c7);xlabel('c3'),ylabel('c7');
    subplot(2,3,5);scatter(c3,c8);xlabel('c3'),ylabel('c8');
    
    elseif data == c4 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 4 vs. 5:8');
    subplot(2,3,1);scatter(c4,c5);xlabel('c4'),ylabel('c5')
    subplot(2,3,2);scatter(c4,c6);xlabel('c4'),ylabel('c6')
    subplot(2,3,3);scatter(c4,c7);xlabel('c4'),ylabel('c7')
    subplot(2,3,4);scatter(c4,c8);xlabel('c4'),ylabel('c8')
    
    elseif data == c5 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 5 vs. 6:8');
    subplot(2,2,1);scatter(c5,c6);xlabel('c5'),ylabel('c6');
    subplot(2,2,2);scatter(c5,c7);xlabel('c5'),ylabel('c7');
    subplot(2,2,3);scatter(c5,c8);xlabel('c5'),ylabel('c8');
    
    elseif data == c6 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 6 vs. 7:8');
    subplot(1,2,1);scatter(c6,c7);xlabel('c6'),ylabel('c7');
    subplot(1,2,2);scatter(c6,c8);xlabel('c6'),ylabel('c8');
    
    elseif data == c7 
    % Subplots  -  Scatter Plot
    suptitle('Attribute 7 vs. 8');
    subplot(1,1,1);scatter(c7,c8);xlabel('c7'),ylabel('c8');
    
    
    end
end

