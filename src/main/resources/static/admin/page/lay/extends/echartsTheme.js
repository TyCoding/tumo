layui.define(function(exports) {
    exports('echartsTheme',{
        "grid":{
            "top":"8%",
            "left":"8%",
            "right":"3%",
            "bottom":"14%"
        },
        "color": [
            "#5a8bff",
            "#00d57b",
            "#f25657",
            "#b57ddc",
            "#ffd87a",
            "#ff9363",
            "#45dfc7"
        ],
        "backgroundColor": "#ffffff",
        "textStyle": {},
        "title": {
            "textStyle": {
                "color": "#666666",
                "color":'#171e2d',
                "fontSize":24,
                "fontWeight":'normal'
            },
            "subtextStyle": {
                "color": "#999999"
            }
        },
        "line": {
            'smoothMonotone':'none',
            'animationDuration':3000,
            'animationEasing':'quarticOut',
            "itemStyle": {
                "normal": {
                    "borderWidth": "5"
                }
            },
            "symbolSize": "8",
            "symbol": "emptyCircle",
            "smooth": true,
            "lineStyle": {
                "normal": {
                    "width": "5"
                }
            }
        },
        "radar": {
            "itemStyle": {
                "normal": {
                    "borderWidth": "5"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "5"
                }
            },
            "symbolSize": "8",
            "symbol": "emptyCircle",
            "smooth": false
        },
        "bar": {
            "itemStyle": {
                "normal": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                },
                "emphasis": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                }
            }
        },
        "pie": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "scatter": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "boxplot": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "parallel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "sankey": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "funnel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "gauge": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "candlestick": {
            "itemStyle": {
                "normal": {
                    "color": "rgba(249,226,226,0.2)",
                    "color0": "rgba(90,139,255,0.2)",
                    "borderColor": "#f25657",
                    "borderColor0": "#5a8bff",
                    "borderWidth": "2"
                }
            }
        },
        "graph": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "1",
                    "color": "#c5cbd9"
                }
            },
            "symbolSize": "8",
            "symbol": "emptyCircle",
            "smooth": false,
            "color": [
                "#5a8bff",
                "#00d57b",
                "#f25657",
                "#b57ddc",
                "#ffd87a",
                "#ff9363",
                "#45dfc7"
            ],
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                }
            }
        },
        "map": {
            "itemStyle": {
                "normal": {
                    "areaColor": "#eeeeee",
                    "borderColor": "#aaaaaa",
                    "borderWidth": 0.5
                },
                "emphasis": {
                    "areaColor": "rgba(90,139,255,0.2)",
                    "borderColor": "#5a8bff",
                    "borderWidth": 1
                }
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#5a8bff"
                    }
                }
            }
        },
        "geo": {
            "itemStyle": {
                "normal": {
                    "areaColor": "#eeeeee",
                    "borderColor": "#aaaaaa",
                    "borderWidth": 0.5
                },
                "emphasis": {
                    "areaColor": "rgba(90,139,255,0.2)",
                    "borderColor": "#5a8bff",
                    "borderWidth": 1
                }
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#5a8bff"
                    }
                }
            }
        },
        "categoryAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#171e2d"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#171e2d"
                }
            },
            "splitLine": {
                "show": false,
                "lineStyle": {
                    "color": [
                        "#ffffff"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "#f9f9f9",
                        "#ffffff"
                    ]
                }
            }
        },
        "valueAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#171e2d"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#171e2d"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#ffffff"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "#f9f9f9",
                        "#ffffff"
                    ]
                }
            }
        },
        "logAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#f1f1f1"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#171e2d"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#ffffff"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "#f9f9f9",
                        "#ffffff"
                    ]
                }
            }
        },
        "timeAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#f1f1f1"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#171e2d"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#ffffff"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "#f9f9f9",
                        "#ffffff"
                    ]
                }
            }
        },
        "toolbox": {
            "iconStyle": {
                "normal": {
                    "borderColor": "#adb3c2"
                },
                "emphasis": {
                    "borderColor": "#171e2d"
                }
            }
        },
        "legend": {
            "textStyle": {
                "color": "#171e2d"
            },
            itemWidth: 8,
            itemHeight: 8,
        },
        "tooltip": {
            "backgroundColor":"rgba(23,30,45,.8)",
            "axisPointer": {
                "lineStyle": {
                    "color": "#adb3c2",
                    "width": "1",
                    "type":'dashed'
                },
                "crossStyle": {
                    "color": "#adb3c2",
                    "width": "1"
                }
            }
        },
        "timeline": {
            "lineStyle": {
                "color": "#c5cbd9",
                "width": "1"
            },
            "itemStyle": {
                "normal": {
                    "color": "#c5cbd9",
                    "borderWidth": "1"
                },
                "emphasis": {
                    "color": "#5a8bff"
                }
            },
            "controlStyle": {
                "normal": {
                    "color": "#171e2d",
                    "borderColor": "#626c91",
                    "borderWidth": "1"
                },
                "emphasis": {
                    "color": "#171e2d",
                    "borderColor": "#626c91",
                    "borderWidth": "1"
                }
            },
            "checkpointStyle": {
                "color": "#5a8bff",
                "borderColor": "rgba(63,177,227,0.15)"
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#626c91"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#626c91"
                    }
                }
            }
        },
        "visualMap": {
            "color": [
                "#5a8bff",
                "#87a9f8",
                "#abc2f8",
                "#d4dffa",
                "#f3f6fe"
            ]
        },
        "dataZoom": {
            "backgroundColor": "rgba(255,255,255,0)",
            "dataBackgroundColor": "rgba(90,139,255,0.1)",
            "fillerColor": "rgba(90,139,255,0.1)",
            "handleColor": "#9fbaf8",
            "handleSize": "80%",
            "textStyle": {
                "color": "#626c91"
            }
        },
        "markPoint": {
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                }
            }
        }
        


    });
});