google.charts.load('current', {packages:['orgchart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Name');
		data.addColumn('string', 'Manager');
		data.addColumn('string', 'ToolTip');

		data.addRows([
			['CustomProcess', '', 'Process'],
			['Disclosure', 'CustomProcess', 'dependency'],
			['ModelInputCashFlow', 'CustomProcess', 'source'],
			['custom', 'CustomProcess', 'output'],
			['DisclosureReportProcess', '', 'Process'],
			['DisclosureExposureOutput', 'DisclosureReportProcess', 'source'],
			['EnrichedCollateral', 'DisclosureReportProcess', 'source'],
			['DisclosureExcoMapping', 'DisclosureReportProcess', 'source'],
			['Disclosure', 'DisclosureReportProcess', 'output'],
			['Disclosure_Control', 'DisclosureReportProcess', 'output'],
			['Disclosure', 'DisclosureReportProcess', 'source']

		]);

		var conf = {
			allowHTML: true,
			allowCollapse: true
		}

		//Create Chart
		var chart = new google.visualization.OrgChart(document.getElementById("chart_div"));
		//Draw and set allowHTML option to true for  tooltips.
		chart.draw(data, conf);
}