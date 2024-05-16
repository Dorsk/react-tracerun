import ReactApexChart from 'react-apexcharts';
import React from 'react';
import moment from 'moment';
import './../../../index.css';

var xmlResult ;

class TraceAppComponent extends React.Component {

    constructor(props) {
      super(props);

      this.updateCellContentState = this.updateCellContentState.bind(this);
      this.updateDataState = this.updateDataState.bind(this);
      this.turnOffGraphState = this.turnOffGraphState.bind(this);
      this.readfileCreateData = this.readfileCreateData.bind(this);
      this.updateState = this.updateState.bind(this);

      this.state = {
        file : null,
        fileCompare : null,
        showGraph : false,
        loading : false,
        xmlDataStatut : null,
        contentCell : null,
        rbConfig : "cell",
        rbConfigLine : "multi",
        height : '475',
        series: [
          {
            data: [
              {
                x: 'MainSession',
                y: [
                  new Date('2024-03-05 10:30:54:364').getTime(),
                  new Date('2024-03-05 10:38:17:354').getTime()
                ],
                fillColor: '#008FFB'
              }
            ]
          },{
            data: [
            ]
          }
        ],
        options: {
          chart: {
            type: 'rangeBar',
            events: {
              click: function(event, chartContext, config) {
                if(config.seriesIndex > -1 && config.dataPointIndex > -1){
                  // show content cell
                  document.getElementById('cellContent').replaceChildren("");
                  document.getElementById('cellName').replaceChildren("");
                  document.getElementById('cellName').appendChild(document.createTextNode(config.config.series[config.seriesIndex].data[config.dataPointIndex].x));
                  document.getElementById('cellContent').appendChild(document.createTextNode(config.config.series[config.seriesIndex].data[config.dataPointIndex].content));
                }
              }
            }
          },
          plotOptions: {
            bar: {
              horizontal: true,
              distributed: true,
              dataLabels: {
                hideOverflowingLabels: false
              }
            }
          },
          tooltip: {
            custom: function({series, seriesIndex, dataPointIndex, w}) {
              var a = moment(w.config.series[seriesIndex].data[dataPointIndex].y[0]);
              var b = moment(w.config.series[seriesIndex].data[dataPointIndex].y[1]);
              var diff = b.diff(a, 'ms')
              return '<div class="arrow_box">' +
                '<span>' + w.globals.labels[dataPointIndex] + ' : ' + diff + ' ms ' +
                '</div>'
            }
          },
          dataLabels: { // OUTLINE à sur charger
            enabled: true,
            formatter: function(val, opts) {
              // var label = opts.w.globals.labels[opts.dataPointIndex]
              var a = moment(val[0])
              var b = moment(val[1])
              var diff = b.diff(a, 'ms')
              return diff + ' ms'
            },
            style: {
              colors: ['#f3f4f5', '#fff']
            }
          },
          xaxis: {
            type: 'datetime'
          },
          yaxis: {
            show: true
          },
          grid: {
            row: {
              colors: ['#f3f4f5', '#fff'],
              opacity: 1
            }
          }
        }
      };
    }

    handleCompareFileChange(file)  {
      // prepare file to compare
      this.setState(currentState => {
        return { fileCompare: file};
      });
    }
    
    handleChange (file)  {
      // Clean datas
      document.getElementById('cellContent').replaceChildren("");
      document.getElementById('cellName').replaceChildren("");
      // prepare new file
      this.setState(currentState => {
        return { file: file, showGraph : false, xmlDataStatut : ''};
      });
    };

    handleClick ()  {
      if(this.state.file == null) {
          console.log(this.state.file);
      } else {
        var reader = new FileReader();
        this.turnOffGraphState();

        // read file to String
        reader.onload = function () {
          this.readfileCreateData(reader, 1, false);
          }.bind(this);
        if(this.state.file) {
          reader.readAsText(this.state.file);
        }
        if (this.state.rbConfigLine === "compare"){
          var readerCompare = new FileReader();
          readerCompare.onload = function () {
            this.readfileCreateData(readerCompare, 2, true);
          }.bind(this);
        
          if(this.state.fileCompare) {
            readerCompare.readAsText(this.state.fileCompare);
          }
        }
      }
    }

    readfileCreateData(reader, color, compareMode){
      xmlResult =  reader.result;
      var dataArray = {};
      dataArray = [];
      var keys = {};
      var i=0;
      const parser = new DOMParser();
      const xml = parser.parseFromString(xmlResult, 'text/xml');
      var cells = xml.getElementsByTagName("cell");

      // traitement affichage session par session
      if (this.state.rbConfig === "session"){

        var cellsArray = Array.from(cells); 
        var result = {};
        var previewSP = "";
        for (i = 0; i <cellsArray.length; i++) {
          // test creation d'un segment unique
          if (result[cellsArray[i].getAttribute("session")] === undefined
            || !result[cellsArray[i].getAttribute("session")]) {
              result[cellsArray[i].getAttribute("session")] = { sp: cellsArray[i].getAttribute("session"),min: new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime(), max: new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime() };
          } else {
            // min
            if (new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime() < result[cellsArray[i].getAttribute("session")].min) {
              if (i+1 === cells.length) {
                result[cellsArray[i].getAttribute("session")].min = new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime() + 10; // +10 ms par défaut
              } else {
                result[cellsArray[i].getAttribute("session")].min = new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime();
              }
            }
            // max
            if (new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime() > result[cellsArray[i].getAttribute("session")].max) {
              if (i+1 === cells.length) {
                result[cellsArray[i].getAttribute("session")].max = new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime() + 10; // +10 ms par défaut
              } else {
                result[cellsArray[i].getAttribute("session")].max = new Date("2024-03-05 " + cellsArray[i].getAttribute("timestamp")).getTime();
              }
            }
          }
        }
        // Build data for the graph
        keys = Object.keys(result);
        keys.map(key => {
          var dataIn = {};
          dataIn.x = result[key].sp;
          dataIn.y = [result[key].min, result[key].max];

          // TODO rendre les couleurs aléatoire
          if(color===1){
            dataIn.fillColor = '#008FFB';
            color=2;
          } else if (color===2){
            dataIn.fillColor = '#FF4560';
            color=3;
          } else if (color===3){
            dataIn.fillColor = '#00E396';
            color=4;
          } else if (color===4){
            dataIn.fillColor = '#0CCED5';
            color=5;
          } else if (color===5){
            dataIn.fillColor = '#F3C406';
            color=1;
          }
          dataArray.push(dataIn);
        });
      }
      // traitement des Cell par cell
      else if(this.state.rbConfig === "cell"){

        var result = {};
        for (i = 0; i <cells.length; i++) {

          var dataIn = {};
          if (this.state.rbConfigLine === "compact"){
            dataIn.x =  "sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session");
            if (result["sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")] === undefined
            || !result["sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")]) {
              result["sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")]= {sp : "sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")};
            }
          } else {
            dataIn.x =  "id-" + cells[i].getAttribute("id") + "_sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session");
            if (result["id-" + cells[i].getAttribute("id") + "_sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")] === undefined
            || !result["id-" + cells[i].getAttribute("id") + "_sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")]) {
              result["id-" + cells[i].getAttribute("id") + "_sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")] = { sp : "id-" + cells[i].getAttribute("id") + "_sp-" +cells[i].getAttribute("sp") + "_" + cells[i].getAttribute("session")};
            }
          }
          if (i+1 === cells.length) {
            dataIn.y = [new Date("2024-03-05 " + cells[i].getAttribute("timestamp")).getTime(), new Date(cells[i].getAttribute("timestamp")).getTime() + 10]; // +10 ms par défaut
          } else {
            dataIn.y = [new Date("2024-03-05 " + cells[i].getAttribute("timestamp")).getTime(), new Date("2024-03-05 " + cells[i+1].getAttribute("timestamp")).getTime()];
          }
          // TODO rendre les couleurs aléatoire
          if(color===1){
            dataIn.fillColor = '#008FFB';
            color=2;
          } else if (color===2){
            dataIn.fillColor = '#FF4560';
            color=3;
          } else if (color===3){
            dataIn.fillColor = '#00E396';
            color=4;
          } else if (color===4){
            dataIn.fillColor = '#0CCED5';
            color=5;
          } else if (color===5){
            dataIn.fillColor = '#F3C406';
            color=1;
          }
          dataIn.content = cells[i].outerHTML;
          dataArray.push(dataIn);
        }
      }
      // update datas before to refresh ui
      keys = Object.keys(result);
      
      if (compareMode === false){
        this.state.series[0].data = (dataArray);
        this.state.series[1].data = [];
        this.updateDataState(this.state.series, keys, false);
      } else if (compareMode === true) {
        this.state.series[1].data = (dataArray);
        this.updateDataState(this.state.series, keys);
        this.updateDataState(this.state.series, keys, true);
      }
      // Event refesh
      
    }
    

  updateDataState(newSeries, keys, compareMode) {
    if(compareMode){
      this.setState(currentState => {
        return { series : newSeries,  showGraph : true, loading : false, height :  300 + (keys.length * 40)}; // 80 min height
      });
    } else {
        this.setState(currentState => {
          return { series : newSeries,  showGraph : true, loading : false, height :  300 + (keys.length * 20)}; // 80 min height
      });
    } 
  }

  turnOffGraphState() {
    // clean all datas
      this.setState(currentState => {
        return { showGraph : false, loading : true};
    });

    document.getElementById('cellContent').replaceChildren("");
    document.getElementById('cellName').replaceChildren("");
  }

  updateCellContentState(e, chart, opts) {
    
    this.setState(currentState => {
      return { cellcontent : chart};
  });
  }
  handleChangeRadioButon(e) {
    this.setState(currentState => {
      return { rbConfig : e.target.value};
  });
    if(e.target.value === "session")
      document.getElementById('radioMultiCompact').style.display ='none';
    else 
      document.getElementById('radioMultiCompact').style.display ='block';
  }
  handleChangeRadioButonLine(e) {
    this.setState(currentState => {
      return { rbConfigLine : e.target.value};
  });
  }
  updateState(text) {
      this.setState(currentState => {
        return { xmlDataStatut : text};
    });
  }

    render() {
      return (
        <div>
             <div>
                <pre>{this.state.xmlDataStatut}</pre>
            </div>
           
            <div class="input-group mb-1">
              <input class="form-control" type="file" id="formFile" onChange={e => this.handleChange(e.target.files[0])} accept="text/xml" ></input>
              <button class="input-group-text" id="inputGroupFileAddon01" onClick={e => this.handleClick()}>Upload</button>
            </div>

            <div class="row">
              <div class="col-sm">
                <div class="form-check">
                  <input class="form-check-input" type="radio" value="cell" checked={this.state.rbConfig === "cell"}
                    onChange={e => this.handleChangeRadioButon(e)} name="rdConfig" id="radioCell" defaultChecked ></input>
                  <label class="form-check-label" for="flexRadioDefault2">
                    Cell
                  </label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" value="session" checked={this.state.rbConfig === "session"}
                    onChange={e => this.handleChangeRadioButon(e)} name="rdConfig" id="radioSession"></input>
                  <label class="form-check-label" for="flexRadioDefault1">
                    Session  
                  </label>
                </div>
              </div>
              <div class="col-sm" id="radioMultiCompact">
                <div class="form-check">
                  <input class="form-check-input" type="radio" value="multi" checked={this.state.rbConfigLine === "multi"}
                    onChange={e => this.handleChangeRadioButonLine(e)} name="rbConfigLine" id="radioMulti" defaultChecked ></input>
                  <label class="form-check-label" for="flexRadioDefault2">
                    Multi lignes
                  </label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" value="compact" checked={this.state.rbConfigLine === "compact"}
                    onChange={e => this.handleChangeRadioButonLine(e)} name="rbConfigLine" id="radioSimple"></input>
                  <label class="form-check-label" for="flexRadioDefault1">
                    Compact
                  </label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" value="compare" checked={this.state.rbConfigLine === "compare"}
                    onChange={e => this.handleChangeRadioButonLine(e)} name="rbConfigLine" id="radiocompare"></input>
                  <label class="form-check-label" for="flexRadioDefault1">
                    Compare with other trace : 
                  </label>
                </div>
                 <input class="form-control" type="file" id="formFile" onChange={e => this.handleCompareFileChange(e.target.files[0])} accept="text/xml" ></input>
              </div>
              <div class="col-sm"></div>{ // div pour d'autres options 
              }
            </div>
            <div class="row">
              <div id="chart" className="col-8">
                { this.state.loading === true ? (
                  <div class="progress">
                    <div class="progress-bar progress-bar-animated progress-bar-striped active" role="progressbar"
                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style={{width:"100%"}}>
                      Loading graph
                    </div>
                  </div>
                  ) : ( <p></p> )
                }
                { this.state.showGraph === true ? (
                    <ReactApexChart options={this.state.options} series={this.state.series} type="rangeBar" height={this.state.height}  />
                  ) : ( <p></p> )
                }
              </div>
              <div className="col-4" style={{top: 400, right:0,  position: 'fixed'}}>
                <h2 id="cellName"> </h2>
                  <pre lang="xml" id="cellContent" className='cellContent'>
                  </pre>
                <div id="html-dist"></div>
              </div>
            </div>
        </div>
      );
    }
  }

  export default TraceAppComponent;