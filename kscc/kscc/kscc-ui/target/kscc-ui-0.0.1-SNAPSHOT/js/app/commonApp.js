define(["base"],function(base){
	var self = {};
	self.refreshGrid = function(grid){
		grid.ajax.reload();
	};
	return self;
});