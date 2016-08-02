% This function takes an unnormalized vector of attribute values
% and returns a vector of values normalized according to 
% the data mean and standard deviation. 
function [nv] = normalize( data )
    % Find the mean and standard deviation of the vector
    mu = mean(data);
    std = max(data);
        
    % Create a vector based on the size of given vector
    nv = size(data);
    
    % As you cant divide 0, as long as more run
    if std > 0
        % Calculate the normalised value
        nv = (data - mu) ./ std;
end