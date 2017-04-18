const BLUE_TEAM = 1, RED_TEAM = 2;

var dataTable = false;
var gyroContainer;

var data = {
	side: '',
	team: 0
};

function switchView() {
	dataTable = !dataTable;
	if (dataTable) {
		$('#diagrams-screen').addClass('hidden');
		$('#data-screen').removeClass('hidden');
	} else {
		$('#data-screen').addClass('hidden');
		$('#diagrams-screen').removeClass('hidden');
	}
}

function switchTeam() {
	if (data.team == BLUE_TEAM) {
		setTeam(RED_TEAM);
	} else {
		setTeam(BLUE_TEAM);
	}
}

function setTeam(team) {
	if (team == BLUE_TEAM) {
		data.team = BLUE_TEAM;
		$('#team-switch').addClass('btn-primary').removeClass('btn-danger').html('On Blue Team');
		NetworkTables.putValue('/SmartDashboard/team', team);
	} else if (team == RED_TEAM) {
		data.team = RED_TEAM;
		$('#team-switch').addClass('btn-danger').removeClass('btn-primary').html('On Red Team');
		NetworkTables.putValue('/SmartDashboard/team', team);
	}
}

function setSide(side) {
	data.side = side;
	NetworkTables.putValue('/SmartDashboard/side', side);
}

function setAutoMethod(method) {
	// body...
}

function Graph(canvas) {
	this.canvas = canvas;
	this.ctx = canvas.getContext('2d');
	this.data = [];
}

Graph.prototype.push = function(x, y) {
	this.data.push({x: x, y: y});
};

Graph.prototype.draw = function(xmin, xmax, ymin, ymax) {
	for (var i = this.data.length - 1; i >= 0; i--) {
		p = this.data[i];
		x = lin_map(p.x, xmin, xmax, 0, this.canvas.width);
		y = lin_map(p.y, xmin, xmax, 0, this.canvas.height);
	}
};

function lin_map(x, a1, a2, b1, b2) {
	return (b2 - a2) * (x - a1) / (b1 - a1) + a2;
}

$(document).ready(function() {

	setTeam(BLUE_TEAM);
	ipc.send('connect', 'roborio-5026.local');

	$('svg').each(function() {
		var self = this;
		$.ajax({
			url: $(self).attr('use'),
		    type: 'GET',
		    dataType: 'text',
		    timeout: 1000,
		    success: function(data) {
				$(self).html(data);
				console.log(self);
			}
		});
	});

	$('.side-chooser > input').click(function() {
		setSide($(this).attr('side'));
	});

	$('#auto-method').change(function() {
		NetworkTables.putValue('/SmartDashboard/side', $(this).val());
	});
});

NetworkTables.addKeyListener('/SmartDashboard/gyro', (key, value) => {
	$('#asdf').html('data');
});