$(function () {
    /* 设置grid默认UI*/
    $.jgrid.defaults.styleUI = 'Bootstrap';
    var mydata = [
        {
            XZQH: "焉耆",
            A: "监测站1",
            B: "500621asdf",
            C: "2017-10-12 08:05:32",
            D: 21,
            E: 75,
            F: 0.75,
            G: "西北风",
            H: "10.5",
            I: "15",
            J: 1
        }, {
            XZQH: "焉耆",
            A: "监测站2",
            B: "500625",
            C: "2017-10-10 08:01:20",
            D: 15,
            E: 80,
            F: 0.75,
            G: "东南风",
            H: "3.0",
            I: "25",
            J: 2
        }
    ]
    $('#table_list').jqGrid({
        data: mydata,
        datatype: "local",
        mtype: 'POST', /* 此次注意用POST方法,GET可能会导致中文参数乱码*/
        multiselect: true, //定义是否可以多选
        height: 500,
        autowidth: true,
        shrinkToFit: false, //此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
        scrollOffset: 5, //设置垂直滚动条宽度  默认18
        rownumbers: true, // show row numbers
        rowNum: 15, /* 默认每页的记录数*/
        rowList: [15, 30, 45], //一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
        colNames: ['行政区划', '站名', '站码', '上报时间', '大气温度(℃)', '湿度(%)', '气压(bar)', '风向', '风速(m/s)', '降雨量(mm/5min)', '光辐射(W/㎡)', '行政区划', '站名', '站码', '上报时间', '大气温度(℃)', '湿度(%)',],
        colModel: [{
            name: 'XZQH',
            sortable: false,
            align: 'right',
            width: 100,
            frozen: true,
        },
            {
                name: 'A',
                sortable: false,
                align: 'right',
                width: 100,
                frozen: true,
                formatter: function (cellvalue) {
                    return '<input type="checkbox" /> adsfalsdfj;'
                }
            },
            {
                name: 'B',
                sortable: false,
                width: 100,
                frozen: true,
                align: 'left'
            },
            {
                name: 'C',
                sortable: false,
                width: 140,
                frozen: true,
                align: 'right'
            },
            {
                name: 'D',
                sortable: false,
                width: 100,
                align: 'right'
            },
            {
                name: 'E',
                sortable: false,
                width: 100,
                align: 'right'
            },
            {
                name: 'F',
                sortable: false,
                width: 100,
                align: 'right'
            },
            {
                name: 'G',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'H',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'I',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            },
            {
                name: 'J',
                sortable: false,
                width: 100,
                align: 'center'
            }
        ],
        pager: '#pager_list', /* 分页渲染对象*/
        viewrecords: true,
        // footerrow: true,
        // userDataOnFooter: true,
        jsonReader: { //描述json 数据格式的数组
            root: "content", //记录列表
            page: "page", //当前页码
            total: "totalPage", //总页数
            records: "totalElements", //总记录数
            repeatitems: false,
            id: 'SRID'
        }
    });

    $("#table_list").jqGrid('setFrozenColumns');

    var allData = $("#table_list").jqGrid("getRowData");
    $.each(allData, function (i, n) {
        // alert(JSON.stringify(n))

    })

    $("#table_list").jqGrid("setGroupHeaders", {
        useColSpanStyle: true,
        groupHeaders: [
            {startColumnName: 'A', numberOfColumns: 3, titleText: '二级表头1'},
            {startColumnName: 'D', numberOfColumns: 2, titleText: '二级表头2'}
        ]
    });

    // jQuery("#table_list").jqGrid("setComplexGroupHeaders", {
    //     complexGroupHeaders: [
    //         {startColumnName: 'A', numberOfColumns: 7, titleText: '基本信息A'}
    //     ]
    // });
})