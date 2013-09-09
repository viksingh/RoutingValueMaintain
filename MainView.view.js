sap.ui.jsview("routingvaluemaintain.MainView", {

	getControllerName : function() {
		return "routingvaluemaintain.MainView";
	},

	createContent : function(oController) {
		
		// create a sample form with two fields and assign a callout to each of them
		var oLayout = new sap.ui.commons.layout.MatrixLayout({
			layoutFixed : false
		});
		
		oLayout.createRow(
				oLabel = new sap.ui.commons.Label({text:"Filename", labelFor:"filename"}),
				oTextField1 = new sap.ui.commons.TextField("filename", {required:true, value:"Filename"})
			);		
		

		oLayout.createRow(
				oLabel = new sap.ui.commons.Label({text:"Business System", labelFor:"busSys"}),
				oTextField2 = new sap.ui.commons.TextField("busSys", {required:true, value:"Business System Name"})
			);

		oLayout.createRow( 	oButton = new sap.ui.commons.Button({ text : "Save",tooltip : "Save Routing Values"})	
		);		
		
		
		
		 oButton.attachPress(oController.doIt);		
		
		
		var oTabStrip1 = new sap.ui.commons.TabStrip("TabStrip1");
		oTabStrip1.setWidth("1900px");
		oTabStrip1.setHeight("200px");
		oTabStrip1.attachClose(function(oEvent) {
			var oTabStrip = oEvent.oSource;
			oTabStrip.closeTab(oEvent.getParameter("index"));
		});


		var aData;
		
		$.ajax({
			url: 'retrieveData',
			type: 'GET',
			dataType: 'json',
			success: function(data){
				
//				console.log('City is'+ data.cityName);
//				console.log('Rest is'+ data.restName);				
//				
//				alert("Call to get data successful");
//				
//
//				var listofSyst = new Array();
//				listofSyst = data;
				for (var i = 0; i< Object.keys(data).length; i++){
					   var item = data[i];
//						var dJson = JSON.stringify(item, null, 2);
//						aData.push(dJson);
//				}
					   aData.push(
						{
							id : item.id,
							filename : item.restName,
							bussys : item.cityName
						}	   
					   );
//					
//				}
				
				


				

				}}});
		
		
		aData = [ {	id : "1",filename : "VIKAS",bussys : "BS_1"	},
	              {	id : "2",filename : "NIENG",bussys : "BS_2"	} ];
		
		var oTable2 = new sap.ui.table.Table({
			title : "Routing Value Maintenance",
			visibleRowCount : 3,
			firstVisibleRow : 1,
			selectionMode : sap.ui.table.SelectionMode.Single,
			navigationMode : sap.ui.table.NavigationMode.Paginator,
			fixedColumnCount : 2
		});

		oTable2.addColumn(new sap.ui.table.Column(
				{
					label : new sap.ui.commons.Label({					text : "Routing Id"
					}),
					template : new sap.ui.commons.TextView().bindProperty(
							"text", "id"),
					sortProperty : "id",
					filterProperty : "id",
					width : "200px"
				}));

		oTable2.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "File Name"
			}),
			template : new sap.ui.commons.TextView().bindProperty("text",
					"filename"),
			sortProperty : "filename",
			filterProperty : "filename",
			width : "200px"
		}));

		oTable2.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Business System"
			}),
			template : new sap.ui.commons.TextView().bindProperty("text",
					"bussys"),
			sortProperty : "bussys",
			filterProperty : "bussys",
			width : "200px"
		}));
		
		
		
		
		
		

		var oModel2 = new sap.ui.model.json.JSONModel();
		oModel2.setData({
			modelData : aData
		});

		oTable2.setModel(oModel2);
		oTable2.bindRows("/modelData");

		// Initially sort the table
		oTable2.sort(oTable2.getColumns()[0]);
		
		oTabStrip1.createTab("Enter Routing Values",oLayout);		
		oTabStrip1.createTab("Display Routing Values",oTable2);
		

		// Bring the table onto the UI
		oTabStrip1.placeAt("content");

	}
});
