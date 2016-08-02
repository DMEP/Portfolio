% Function takes a vector of attribute values, 
% and assigns each value to one of the k bins. 
% Returns the corresponding elements in values v 
% and the bin number binN
 function [binNumber, binCentre] = discretize_attribute(data)
 
 % Define Bin Specifications
 nbins = 10;
 
 % Define the Bin Edges 
 % Generates nbin+1 point, the spacing between is ((max()-min())/(nbins-1))
 binEdges = linspace(min(data),max(data),nbins+1); % bin edges
 binLower = binEdges(1:end-1);
 binUpper = binEdges(2:end);
 binCentre = (binUpper+binLower)./2;
  
 % Assign values to bins
 [~,binNumber] = histcounts(data,binEdges);
 
end
