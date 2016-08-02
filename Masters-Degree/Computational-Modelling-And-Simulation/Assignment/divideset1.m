%  Divides a matrix into two nonoverlapping subsets.
%  Each record from the matrix has a random probability p 
%  to be put into the training set everything else into testing set.
%  Training and Testing sets size will be determined by p.
function [ trd, ted ] = divideset1( data, p_train )
    % Create empty matrixes for output 
    trd = [];
    ted = [];
    % For loop to iterate length of data      
    for i = 1:length(data) 
        % If the remainder is less than p_train         
        if( mod(randn(1), 1) < p_train ) % Use mod to remove negative rand
            % Place the row into a concatenated array for train              
            % Concates on 1 to keep columns between empty trd and data rows
            trd = cat(1, trd, data(i, :)); 
        % else if larger than p_train
        else
            % Place the row into a concatenated array for test
            % Concates on 1 to keep columns between empty ted and data rows
            ted = cat(1, ted, data(i, :));
        end
    end
end