%  Divides the matrix into two nonoverlapping subsets.
%  Each record from the matrix has a random probability p 
%  to be put into the training set everything else into testing set.
function [ trd, ted ] = divideset2( data, p_train )
    %  Training and Testing sets size will be determined by p but will always
    %  be 507 for training and 261 for testing.
    
    % Create Max training and testing
    % Use ceil to round elements to a positive value
    mte = ceil( p_train * length(data));
    % Use floor to round elements to a negative value
    mtr = floor( (1 - p_train) * length(data));

    
    s = size(data, 2);
    % Create an array of zeros of mte by s
    trd = zeros(mte, s);
    % Create an array of zeros of mtr by s
    ted = zeros(mtr, s);
    % Create a counters for mtr and mte
    mtrc = 1; mtec = 1;
    
    % The rows will be randomly placed into a subset 
    % until the record limit has been reached.
    for i = 1:length(data)
        % Use mod to remove negative rand
        % If the remainder is less than p_train and mtr or if mtec is 0
        if( (mod(randn(1), 1) < p_train && mtr > 0) || mte == 0)
             % Place the row into ted
            ted(mtrc, :) = data(i, :);
            % Add one to the counter
            mtrc = mtrc + 1;
            % Take 1 away from the mtr
            mtr = mtr - 1;
        else
           % Place the row into trd
            trd(mtec, :) = data(i, :);
            % Add one to the counter
            mtec = mtec + 1;
            % Take 1 away from the mte
            mte = mte - 1;
        end
    end
end