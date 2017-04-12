var dataTable = false;

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