define(["jquery", "bootstrap", "jqueryUI"], function() {
	var a = {};
	a.$scope = {};
	a.$scope.entities = [];
	a.tagLib = "li,em,map,ul,div,span,h1,h2,h3,h4,h5,h6,ol,img,canvas,label,dl,dt";
	a.msgGroup = {};
	a.$handle = {};
	a.IE = function() {
		if (window.XMLHttpRequest == "undefined") {
			return 6
		} else {
			if (!$.support.style) {
				return 7
			} else {
				if (!$.support.opacity) {
					return 8
				} else {
					return 9
				}
			}
		}
	}();
	a.isIE = function() {
		if (document.all) {
			return true
		} else {
			return false
		}
	}();
	a.console = function(b) {
		if (!a.isIE) {
			console.log(b)
		}
	};
	a.error = function(c, b) {
		if (!a.isIE) {
			console.log("<" + c + "错误>:" + b)
		}
	};
	a.renderFucGroup = {
		before: [],
		after: []
	};
	a.render = function(c) {
		var b = {};
		b.beforeRenderCallback = function() {
			$(a.renderFucGroup.before).each(function(d, e) {
				e()
			})
		};
		b.afterRenderCallback = function() {
			$(a.renderFucGroup.after).each(function(d, e) {
				e()
			})
		};
		b.process = function(h) {
			if (h) {
				if (h.option) {
					var g = h.option;
					var f = g.type ? g.type : "custom";
					var e = a;
					var d = h.container ? h.container : $("body");
					$(f.split(".")).each(function(j, k) {
						if (!e[k]) {
							if (f.split(".").length == 1) {
								if (a.tagLib.indexOf(f) > -1) {
									e = a.custom;
									g.customType = g.type;
									g.type = "custom"
								} else {
									a.console('"' + f + '"是无效的标签');
									e = null
								}
							}
							return false
						} else {
							e = e[k]
						}
					});
					if (e) {
						if (g.beforeCreateCallback) {
							a.val(g.beforeCreateCallback)(g)
						}
						var i = e({
							container: d,
							option: g
						});
						if (i.create) {
							i.create()
						}
						if (g.afterCreateCallback) {
							a.val(g.afterCreateCallback)(i, g)
						}
						if (g.items && g.items.length > 0) {
							$(g.items).each(function(j, k) {
								b.process({
									container: i.element,
									option: k
								})
							})
						}
					}
				}
			}
		};
		b.beforeRenderCallback();
		b.process(c);
		b.afterRenderCallback()
	};
	a.page = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (!c) {
			b.container = $("body")
		} else {
			b.container = c.container ? c.container : $("body");
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			b.element = $("body");
			a.setProperty(b);
			return b
		};
		return b
	};
	a.header = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (!c) {
			b.container = $("body")
		} else {
			b.container = c.container ? c.container : $("body");
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (a.IE > 8) {
				b.element = document.createElement("header")
			} else {
				b.element = document.createElement("div");
				$(b.element).addClass("header")
			}
			a.setProperty(b);
			$(b.container).append(b.element);
			return b
		};
		return b
	};
	a.section = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (!c) {
			b.container = $("body")
		} else {
			b.container = c.container ? c.container : $("body");
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (a.IE > 8) {
				b.element = document.createElement("section")
			} else {
				b.element = document.createElement("div");
				$(b.element).addClass("section")
			}
			a.setProperty(b);
			$(b.container).append(b.element);
			return b
		};
		return b
	};
	a.footer = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (!c) {
			b.container = $("body")
		} else {
			b.container = c.container ? c.container : $("body");
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (a.IE > 8) {
				b.element = document.createElement("footer")
			} else {
				b.element = document.createElement("div");
				$(b.element).addClass("footer")
			}
			a.setProperty(b);
			$(b.container).append(b.element);
			return b
		};
		return b
	};
	a.custom = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (!b.option.customType) {
				b.element = document.createElement("div")
			} else {
				b.element = document.createElement(b.option.customType)
			}
			a.setProperty(b);
			$(b.container).append(b.element);
			return b
		};
		return b
	};
	a.setProperty = function(f) {
		var e = f.element;
		var c = f.option;
		if (c) {
			if (c.id) {
				$(e).attr("id", c.id)
			}
			if (c.name) {
				switch ($(e).get(0).tagName.toLowerCase()) {
				case "input":
				case "select":
				case "textarea":
					$(e).attr("name", c.name);
					break;
				default:
					$(e).addClass(c.name);
					break
				}
			}
			if (c.cls) {
				$(e).addClass(c.cls.replace(/,/g, " "))
			}
			if (c.style) {
				$(e).attr("style", c.style.replace(/,/g, ";"))
			}
			if (c.show == undefined || c.show) {} else {
				$(e).hide()
			}
			if (c.width) {
				$(e).css("width", c.width)
			}
			if (c.title) {
				$(e).attr("title", c.title)
			}
			if (c.height) {
				$(e).css("height", c.height)
			}
			if (c.value) {
				$(e).val(a.val(c.value))
			}
			if (c.readonly) {
				$(e).attr("readonly", "readonly")
			}
			if (c.disabled) {
				$(e).attr("disabled", "disabled")
			}
			if (c.attr) {
				$(c.attr.split(",")).each(function(h, j) {
					var g = j.split("::");
					if (g.length > 1) {
						$(e).attr(g[0], a.val(g[1]))
					}
				})
			}
			if (c.html) {
				if (c.type && c.type.split(".")[0] == "button") {
					$(e).append("<span>" + a.val(c.html) + "</span>")
				} else {
					$(e).html(a.val(c.html))
				}
			}
			if (c.text) {
				if (c.type && c.type.split(".")[0] == "button") {
					$(e).append("<span>" + a.val(c.text) + "</span>")
				} else {
					$(e).text(a.val(c.text))
				}
			}
			if (c.target) {
				$(e).attr("target", c.target)
			}
			if (c.src) {
				$(e).attr("src", c.src)
			}
			if (c.method) {
				$(e).attr("method", c.method)
			}
			if (c.action) {
				$(e).attr("action", c.action)
			}
			if (c.placeholder) {
				$(e).attr("placeholder", c.placeholder)
			}
			if (c.clickCallback) {
				var b = a.val(c.clickCallback);
				$(e).on("click", function() {
					if (typeof(b) == "function") {
						b(e, f)
					} else {
						a.console("该类型不是方法！")
					}
				})
			}
			if (c.changeCallback) {
				var b = a.val(c.changeCallback);
				$(e).on("change", function() {
					if (b) {
						b(e, f)
					}
				})
			}
			if (c.rule) {
				var d = a.val(c.rule);
				$(e).attr("rule", c.rule)
			}
			if (c.requiredTip && c.requiredTip == "true") {
				$(f.formGroup).append("<em class='fableFactory-form-isRequired'>*</em>")
			}
			if (c.formTip) {
				$(f.formGroup).append("<em class='fableFactory-form-tip'>(" + c.formTip + ")</em>")
			}
		}
	};
	a.val = function(d) {
		var b = {};
		b.analyze = function(h) {
			var e = "";
			switch (h.substr(0, 1).toLowerCase()) {
			case "#":
				var f = null;
				if ($(h.split("::")[0])) {
					f = $(h.split("::")[0])
				}
				if (!f || f.length < 1) {
					return ""
				}
				if (h.split("::").length > 1) {
					var i = h.split("::")[1];
					switch (i) {
					case "val":
						e = f.val();
						break;
					case "html":
						e = f.html();
						break;
					case "text":
						e = f.text();
						break
					}
					if (i.substr(0, 1).toLowerCase() == "@") {
						e = f.attr(i.split("@")[1])
					}
				} else {
					e = $(h)
				}
				break;
			case ".":
				var f = null;
				var g = h.split("::");
				if (g[0].split("?").length > 1) {
					if (!isNaN(g[0].split("?")[1])) {
						f = $(g[0].split("?")[0] + ":eq(" + g[0].split("?")[1] + ")")
					} else {
						f = $(g[0].split("?")[0])
					}
				} else {
					f = $(g[0])
				}
				if (!f || f.length < 1) {
					return ""
				}
				if (g.length > 1) {
					var i = g[1];
					if (i.substr(0, 1).toLowerCase() == "@") {
						e = f.attr(i.split("@")[1])
					} else {
						switch (i) {
						case "val":
							e = f.val();
							break;
						case "html":
							e = f.html();
							break;
						case "text":
							e = f.text();
							break
						}
					}
				} else {
					e = $(h)
				}
				break;
			default:
				e = (a.$scope)[h] ? (a.$scope)[h] : null;
				break
			}
			return e
		};
		if (!d) {
			return ""
		}
		if (typeof(d) != "string") {
			return d
		} else {
			var c = null;
			if (d.split("{{").length < 2) {
				c = d
			} else {
				if (d.split("{{").length == 2 && d.split("}}").length == 2) {
					c = b.analyze(d.replace(/{{/, "").replace(/}}/, ""))
				} else {
					c = "";
					$(d.split("{{")).each(function(f, g) {
						if (g.split("}}").length > 1) {
							var e = g.split("{{")[0];
							c += b.analyze(e.split("}}")[0]) + g.split("}}")[1]
						} else {
							c += g
						}
					})
				}
			}
			return c
		}
	};
	a.link = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.container && b.option) {
				b.element = document.createElement("a");
				a.setProperty(b);
				$(b.container).append(b.element)
			}
			return b
		};
		return b
	};
	a.form = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.container && b.option) {
				b.element = document.createElement("form");
				$(b.element).attr("role", "form");
				if (b.option.data) {
					b.option.items = a.val(b.option.data)
				}
				if (b.option.layout) {
					$(b.element).addClass("form-" + b.option.layout)
				}
				a.setProperty(b);
				$(b.container).append(b.element)
			}
			return b
		};
		return b
	};
	a.form.input = function(c) {
		var b = {};
		b.domContainer = null;
		b.element = null;
		b.label = null;
		b.formGroup = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element)
			} else {
				if (b.container && b.option) {
					b.createFormGroup();
					b.createLabel();
					b.createElement()
				}
			}
			a.setProperty(b);
			return b
		};
		b.createFormGroup = function() {
			b.formGroup = document.createElement("div");
			$(b.formGroup).addClass("form-group fableFactory-form-group");
			$(b.container).append(b.formGroup)
		};
		b.labelClass = "control-label";
		b.createLabel = function() {
			if (b.option.label) {
				b.label = document.createElement("label");
				if (b.option.id) {
					$(b.label).attr("for", b.option.id)
				}
				$(b.label).html(b.option.label + "：");
				$(b.formGroup).append(b.label);
				if (b.option.col) {
					var d = b.option.col.split(",");
					if (!isNaN(d[0])) {
						$(b.label).addClass("col-sm-" + d[0])
					}
				}
				if (b.option.labelWidth) {
					$(b.label).css("width", b.option.labelWidth + "px")
				}
				$(b.label).addClass(b.labelClass)
			}
		};
		b.elementClass = "form-control";
		b.createElement = function() {
			b.element = document.createElement("input");
			$(b.element).addClass(b.elementClass);
			if (b.option.col) {
				b.domContainer = document.createElement("div");
				var d = b.option.col.split(",");
				if (!isNaN(d[1])) {
					$(b.domContainer).addClass("col-sm-" + d[1])
				}
				$(b.formGroup).append(b.domContainer);
				$(b.domContainer).append(b.element)
			} else {
				$(b.formGroup).append(b.element)
			}
		};
		return b
	};
	a.form.input.password = function(c) {
		var b = a.form.input.call(this, c);
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element);
				b.set()
			} else {
				if (b.container && b.option) {
					b.createFormGroup();
					b.createLabel();
					b.createElement();
					b.set()
				}
			}
			return b
		};
		b.set = function() {
			$(b.element).attr("type", "password");
			a.setProperty(b)
		};
		return b
	};
	a.form.input.date = function(c) {
		var b = a.form.input.call(this, c);
		b.option = c.option ? c.option : null;
		b.dateOption = b.option.dateOption ? b.option.dateOption : null;
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element);
				b.set()
			} else {
				if (b.container && b.option) {
					b.createFormGroup();
					b.createLabel();
					b.createElement();
					b.set()
				}
			}
			return b
		};
		b.set = function() {
			require(["date"], function() {
				$(b.element).addClass("laydate-icon");
				$(b.element).attr("readonly", "readonly");
				$(b.element).css("cursor", "pointer");
				$(b.element).on("click", function() {
					laydate(b.dateOption)
				});
				a.setProperty(b)
			})
		};
		return b
	};
	a.form.input.hidden = function(c) {
		var b = a.form.input.call(this, c);
		b.elementClass = "";
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element);
				b.set()
			} else {
				if (b.container && b.option) {
					b.createElement()
				}
			}
			return b
		};
		b.createElement = function() {
			b.element = document.createElement("input");
			$(b.element).attr("type", "hidden");
			$(b.container).append(b.element)
		};
		return b
	};
	a.form.input.file = function(c) {
		var b = a.form.input.call(this, c);
		b.elementClass = "";
		b.labelClass = "";
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element);
				b.set()
			} else {
				if (b.container && b.option) {
					b.createFormGroup();
					b.createLabel();
					b.createElement();
					b.set()
				}
			}
			return b
		};
		b.set = function() {
			$(b.element).css("display", "inline-block");
			$(b.element).attr("type", "file")
		};
		return b
	};
	a.form.select = function(c) {
		var b = {};
		b.domContainer = null;
		b.element = null;
		b.label = null;
		b.formGroup = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element)
			} else {
				if (b.container && b.option) {
					b.createFormGroup();
					b.createLabel();
					b.createElement()
				}
			}
			a.setProperty(b);
			return b
		};
		b.createFormGroup = function() {
			b.formGroup = document.createElement("div");
			$(b.formGroup).addClass("form-group fableFactory-form-group");
			$(b.container).append(b.formGroup)
		};
		b.createLabel = function() {
			if (b.option.label) {
				b.label = document.createElement("label");
				if (b.option.id) {
					$(b.label).attr("for", b.option.id)
				}
				$(b.label).html(b.option.label + "：");
				$(b.formGroup).append(b.label);
				if (b.option.col) {
					var d = b.option.col.split(",");
					if (!isNaN(d[0])) {
						$(b.label).addClass("col-sm-" + d[0])
					}
				}
				$(b.label).addClass("control-label")
			}
		};
		b.createElement = function() {
			b.element = document.createElement("select");
			if (b.option.domType) {
				$(b.element).attr("type", b.option.domType)
			}
			$(b.element).addClass("form-control");
			if (b.option.data) {
				b.createOptions(a.val(b.option.data))
			} else {
				if (b.option.remoteUrl) {
					a.remote({
						url: a.val(b.option.remoteUrl),
						params: a.getObj(b.option.remoteParams),
						successCallback: function(e) {
							b.createOptions(e)
						}
					})
				}
			}
			if (b.option.col) {
				b.domContainer = document.createElement("div");
				var d = b.option.col.split(",");
				if (!isNaN(d[1])) {
					$(b.domContainer).addClass("col-sm-" + d[1])
				}
				$(b.formGroup).append(b.domContainer);
				$(b.domContainer).append(b.element)
			} else {
				$(b.formGroup).append(b.element)
			}
		};
		b.createOptions = function(e) {
			$(b.element).append("<option value='-1'>*请选择*</option>");
			var d = false;
			$(e).each(function(f, h) {
				var g = document.createElement("option");
				$(g).attr("value", h.value);
				$(b.element).append(g);
				if (h.selected) {
					d = true;
					$(g).attr("selected", true)
				} else {
					if (!d) {
						if (b.option.selected && !isNaN(b.option.selected)) {
							if (b.option.selected == f) {
								$(g).attr("selected", true)
							}
						} else {
							if (b.option.selectedValue) {
								if (h.value == a.val(b.option.selectedValue)) {
									$(g).attr("selected", true)
								}
							}
						}
					}
				}
				$(g).text(h.text)
			})
		};
		return b
	};
	a.getObj = function(b, d) {
		var c = {};
		if (!b) {
			return c
		}
		if (typeof(b) != "string") {
			return b
		} else {
			if (d) {
				$(b.split(",")).each(function(g, h) {
					var l = h.split("::");
					if (l.length > 1) {
						var j = false;
						for (var f in d) {
							if (l[0] == f) {
								c[l[0]] = d[f];
								j = true
							}
						}
						if (!j) {
							var e = a.val(l[1]);
							if (e == "true") {
								c[l[0]] = true
							} else {
								if (e == "false") {
									c[l[0]] = false
								} else {
									if (!isNaN(e)) {
										c[l[0]] = Number(e)
									} else {
										c[l[0]] = a.val(l[1])
									}
								}
							}
						}
					}
				})
			} else {
				$(b.split(",")).each(function(f, g) {
					var h = g.split("::");
					if (h.length > 1) {
						var e = a.val(h[1]);
						if (e == "true") {
							c[h[0]] = true
						} else {
							if (e == "false") {
								c[h[0]] = false
							} else {
								if (!isNaN(e)) {
									c[h[0]] = Number(e)
								} else {
									c[h[0]] = a.val(h[1])
								}
							}
						}
					}
				})
			}
		}
		return c
	};
	a.remote = function(c) {
		var b = {};
		if (c) {
			b.params = c.params ? c.params : {};
			b.url = c.url ? c.url : "";
			b.async = c.async ? c.async : true;
			b.type = c.type ? c.type : "get";
			b.dataType = c.dataType ? c.dataType : "json";
			b.successCallback = c.successCallback ? c.successCallback : null;
			b.errorCallback = c.errorCallback ? c.errorCallback : null;
			b.beforeCallback = c.beforeCallback ? c.beforeCallback : null;
			b.timeout = c.timeout ? c.timeout : -1;
			b.remoteObj = null;
			b.isLongConnection = c.isLongConnection ? c.isLongConnection : false;
			if (b.isLongConnection) {
				b.timeout = 0
			}
			b.isConnect = true;
			b.connect = function() {
				b.remoteObj = $.ajax({
					type: b.type,
					async: b.async,
					url: b.url,
					contentType: function() {
						if (b.type.toLowerCase() == "post") {
							return "application/json; charset=utf-8"
						} else {
							return null
						}
					}(),
					data: function() {
						if (b.type.toLowerCase() == "post") {
							return JSON.stringify(b.params)
						} else {
							return b.params
						}
					}(),
					dataType: b.dataType,
					timeout: b.timeout,
					success: function(d) {
						if (b.successCallback) {
							if (typeof(b.successCallback) == "string") {
								if (a.$scope[b.successCallback]) {
									a.$scope[b.successCallback](d)
								}
							} else {
								b.successCallback(d)
							}
							if (b.isLongConnection) {
								if (b.isConnect) {
									b.connect()
								}
							}
						}
					},
					error: function(d, f, e) {
						if (b.errorCallback) {
							if (typeof(b.successCallback) == "string") {
								if (a.$scope[b.errorCallback]) {
									a.$scope[b.errorCallback]()
								}
							} else {
								b.errorCallback()
							}
							if (b.isLongConnection) {
								if (b.isConnect) {
									b.connect()
								}
							}
						}
					},
					beforeSend: function(d) {
						if (b.beforeCallback) {
							if (typeof(b.successCallback) == "string") {
								if (a.$scope[b.beforeCallback]) {
									a.$scope[b.beforeCallback]()
								}
							} else {
								b.beforeCallback()
							}
						}
					},
					complete: function(d, e) {
						if (e == "timeout") {
							if (!b.isLongConnection) {
								b.remoteObj.abort()
							}
						}
					}
				});
				return b
			};
			b.stop = function() {
				b.isConnect = false;
				b.remoteObj.abort()
			};
			b.run = function() {
				b.isConnect = true;
				b.connect()
			};
			b.connect();
			return b
		}
	};
	a.form.select.selectGroup = function(c) {
		var b = {};
		b.domContainer = null;
		b.domGroup = [];
		b.label = null;
		b.formGroup = null;
		b.container = null;
		b.option = null;
		b.element = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.container && b.option) {
				b.createFormGroup();
				b.createLabel();
				b.createInit();
				b.entity = a.form.input.hidden({
					container: b.formGroup,
					option: {
						name: b.option.name
					}
				}).create();
				b.element = b.entity.element
			}
			return b
		};
		b.createInit = function(e, d) {
			if (b.option.data) {
				b.createElement(a.val(b.option.data), e, d)
			} else {
				if (b.option.remoteData) {
					var f = null;
					if (b.option.key && e) {
						f = {};
						f[b.option.key] = e
					}
					a.remote({
						url: a.val(b.option.remoteData),
						params: a.getObj(b.option.params, f),
						successCallback: function(g) {
							b.createElement(g, e, d)
						}
					})
				}
			}
		};
		b.createElement = function(i, e, f) {
			var d = null;
			var j = function(n, m) {
					$(n).each(function(p, q) {
						if (d) {
							return false
						}
						if (q.value == m) {
							if (q.items && q.items.length > 0) {
								d = q.items
							}
							return false
						}
						if (q.items && q.items.length > 0) {
							j(q.items, m)
						}
					})
				};
			if (!e) {
				d = i
			} else {
				j(i, e)
			}
			if (d) {
				var l = document.createElement("select");
				if (b.option.domType) {
					$(l).attr("type", b.option.domType)
				}
				$(l).addClass("form-control");
				if (b.option.col) {
					var k = document.createElement("div");
					var g = b.option.col.split(",");
					if (!isNaN(g[1])) {
						$(k).addClass("col-sm-" + g[1])
					}
					$(b.formGroup).append(k);
					$(k).append(l)
				} else {
					$(b.formGroup).append(l)
				}
				l.selId = a.getRandom(1000, 9999);
				if (f) {
					$(f).after(l)
				}
				b.domGroup.push(l);
				var h = function() {
						var m = "";
						$(b.element).val("");
						$(b.domGroup).each(function(n, p) {
							if ($(p).val() != "-1") {
								m += $(p).val() + ","
							}
						});
						$(b.element).val(a.clearLastCharacter(m))
					};
				$(l).on("change", function() {
					$(this).nextAll("select").remove();
					if ($(this).val() != "-1") {
						b.createInit($(this).val(), this)
					} else {
						for (var n = 0, m = b.domGroup.length; n < m; n++) {
							if (b.domGroup[n].selId == l.selId) {
								b.domGroup = b.domGroup.splice(0, n + 1);
								break
							}
						}
					}
					h()
				});
				$(l).append("<option value='-1'>*请选择*</option>");
				$(d).each(function(m, p) {
					var n = document.createElement("option");
					$(n).attr("value", p.value);
					if (p.selected) {
						$(n).attr("selected", true)
					}
					$(n).text(p.text);
					$(l).append(n)
				})
			}
		};
		b.createFormGroup = function() {
			b.formGroup = document.createElement("div");
			$(b.formGroup).addClass("form-group fableFactory-form-group");
			$(b.container).append(b.formGroup)
		};
		b.createLabel = function() {
			if (b.option.label) {
				b.label = document.createElement("label");
				if (b.option.id) {
					$(b.label).attr("for", b.option.id)
				}
				$(b.label).html(b.option.label + "：");
				$(b.formGroup).append(b.label);
				if (b.option.col) {
					var d = b.option.col.split(",");
					if (!isNaN(d[0])) {
						$(b.label).addClass("col-sm-" + d[0])
					}
				}
				$(b.label).addClass("control-label")
			}
		};
		return b
	};
	a.clearLastCharacter = function(b) {
		b = b.substring(0, b.length - 1);
		return b
	};
	a.getRandom = function(c, b) {
		return Math.round(Math.random() * (b - c) + c)
	};
	a.validate = function(B, s) {
		var c = true;
		var l = null;
		if (!s) {
			s = $("body")
		}
		$("#errorMsg").remove();
		var y = function(n) {
				return "<div id='errorMsg' style='background:'><i class='ion-android-cancel'></i><span style='color:red'>" + n + "</span><i class='ion-android-arrow-dropdown'></i></div>"
			};
		if (B) {
			for (var u in B) {
				var r = $(s).find("[name='" + u + "']");
				if (r) {
					var p = a.getObj(B[u]);
					switch ($(r)[0].tagName.toLowerCase()) {
					case "input":
						var f = "text";
						if ($(r).attr("type")) {
							f = $(r).attr("type").toLowerCase()
						}
						switch (f) {
						case "text":
						case "password":
							for (var i in p) {
								switch (i) {
								case "isRequired":
								case "required":
									if (p[i]) {
										if (!$(r).val()) {
											$(r).parent().append(y("必填项"));
											c = false;
											return false
										}
									}
									break;
								case "length":
									if ($(r).val().length > p[i]) {
										$(r).parent().append(y("不能超过" + p[i] + "个字符"));
										c = false;
										return false
									}
									break;
								case "number":
									var x = p[i];
									var A = /^\d+$/;
									if ($.isArray(x)) {
										if (!A.test($(r).val())) {
											$(r).parent().append(y("非整数"));
											c = false;
											return false
										} else {
											if ($(r).val() <= 0) {
												$(r).parent().append(y("不能为0或负数"));
												c = false;
												return false
											}
											if ($(r).val() < x[0]) {
												$(r).parent().append(y("数字过小"));
												c = false;
												return false
											}
											if ($(r).val() > x[1]) {
												$(r).parent().append(y("数字过大"));
												c = false;
												return false
											}
										}
									} else {
										if (x) {
											if (!A.test($(r).val())) {
												$(r).parent().append(y("非整数"));
												c = false;
												return false
											}
											if ($(r).val() <= 0) {
												$(r).parent().append(y("不能为0或负数"));
												c = false;
												return false
											}
										}
									}
									break;
								case "floating":
									var d = /^\d+(\.\d+)?$/;
									var x = p[i];
									if ($.isArray(x)) {
										if (!d.test($(r).val())) {
											$(r).parent().append(y("非数字"));
											c = false;
											return false
										} else {
											if ($(r).val() <= 0) {
												$(r).parent().append(y("不能为0或负数"));
												c = false;
												return false
											}
											if ($(r).val() < x[0]) {
												$(r).parent().append(y("数字过小"));
												c = false;
												return false
											}
											if ($(r).val() > x[1]) {
												$(r).parent().append(y("数字过大"));
												c = false;
												return false
											}
										}
									} else {
										if (x) {
											if (!d.test($(r).val())) {
												$(r).parent().append(y("非数字"));
												c = false;
												return false
											}
											if ($(r).val() <= 0) {
												$(r).parent().append(y("不能为0或负数"));
												c = false;
												return false
											}
										}
									}
									break;
								case "isMoney":
									var t = /^\d+\.?\d{1,2}$/;
									var q = $(r).val();
									if (p[i]) {
										if (!t.test(q)) {
											$(r).parent().append(y("金额格式不符"));
											c = false;
											return false
										}
									}
									break;
								case "isMobile":
									var e = $(r).val();
									var v = /(1[3-9]\d{9}$)/;
									if (p[i]) {
										if (!v.test(e)) {
											$(r).parent().append(y("请填写正确的手机号码"));
											c = false;
											return false
										}
									}
									break;
								case "isTelephone":
									var h = $(r).val();
									var m = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
									if (p[i]) {
										if (!m.test(h)) {
											$(r).parent().append(y("请填写正确的电话号码"));
											c = false;
											return false
										}
									}
									break;
								case "isEmail":
									var z = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
									var w = $(r).val();
									if (p[i]) {
										if (!z.test(w)) {
											$(r).parent().append(y("请填写正确的邮箱地址"));
											c = false;
											return false
										}
									}
									break;
								case "isFliter":
									var k = /^\w+$/;
									if (p[i]) {
										if (!k.test($(r).val())) {
											$(r).parent().append(y("只允许字母、数字、下划线"));
											c = false;
											return false
										}
									}
									break;
								case "card":
									var g = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
									if (p[i]) {
										if (g.test($(r).val()) === false) {
											$(r).parent().append(y("身份证填写错误"));
											return false
										} else {
											var j = {
												11: "北京",
												12: "天津",
												13: "河北",
												14: "山西",
												15: "内蒙古",
												21: "辽宁",
												22: "吉林",
												23: "黑龙江",
												31: "上海",
												32: "江苏",
												33: "浙江",
												34: "安徽",
												35: "福建",
												36: "江西",
												37: "山东",
												41: "河南",
												42: "湖北",
												43: "湖南",
												44: "广东",
												45: "广西",
												46: "海南",
												50: "重庆",
												51: "四川",
												52: "贵州",
												53: "云南",
												54: "西藏",
												61: "陕西",
												62: "甘肃",
												63: "青海",
												64: "宁夏",
												65: "新疆",
												71: "台湾",
												81: "香港",
												82: "澳门",
												91: "国外"
											};
											var b = $(r).val().substring(0, 2);
											if (j[b] == undefined) {
												$(r).parent().append("<div class='errorTip'><i class='ion-android-cancel'></i>无效的身份证格式</div>");
												return false
											}
										}
									}
									break
								}
							}
							break;
						case "radio":
						case "checkbox":
							for (var i in p) {
								switch (i) {
								case "isRequired":
								case "required":
									if (a.getCheckbox(o.name).length == 0) {
										if ($(r).parent().hasClass("inputGroup")) {
											$(r).parent().append(y("必选项"))
										}
										if ($(r).parent().parent().hasClass("inputGroup")) {
											$(r).parent().append(y("必选项"))
										}
										c = false;
										return false
									}
									break
								}
							}
							break
						}
						break;
					case "select":
						for (var i in p) {
							switch (i) {
							case "isRequired":
							case "required":
								if ($(r).val() == "-1") {
									$(r).parent().append(y("必选项"));
									c = false;
									return false
								}
								break
							}
						}
						break;
					case "textarea":
						for (var i in p) {
							switch (i) {
							case "isRequired":
							case "required":
								if (!$(r).val()) {
									$(r).parent().append(y("必选项"));
									c = false;
									return false
								}
								break;
							case "isFliter":
								var k = /^\w+$/;
								if (p[i]) {
									if (!k.test($(r).val())) {
										$(r).parent().append(y("只允许字母、数字、下划线"));
										c = false;
										return false
									}
								}
								break;
							case "length":
								if ($(r).val().length > p[i]) {
									$(r).parent().append(y("不能超过" + p[i] + "个字符"));
									c = false;
									return false
								}
								break
							}
						}
						break
					}
				}
			}
		}
		return c
	};
	a.button = function(c) {
		var b = {};
		b.element = null;
		b.container = null;
		b.option = null;
		if (c) {
			b.container = c.container;
			b.option = c.option ? c.option : null
		}
		b.create = function() {
			if (b.option.element) {
				b.element = $(b.option.element)
			} else {
				if (b.container && b.option) {
					b.createElement()
				}
			}
			a.setProperty(b);
			return b
		};
		b.createElement = function() {
			b.element = $(document.createElement("button"));
			if (b.option.cls) {
				$(b.element).addClass(b.option.cls)
			} else {
				$(b.element).addClass("btn btn-primary")
			}
			$(b.container).append(b.element)
		};
		return b
	};
	a.button.sh = function(c) {
		var b = a.button.call(this, c);
		b.elementClass = "";
		b.labelClass = "";
		b.create = function() {
			if (b.container && b.option) {
				b.createElement();
				b.set()
			}
			a.setProperty(b);
			return b
		};
		b.set = function() {
			b.isOpen = b.option.isOpen ? b.option.isOpen : "true";
			if (b.isOpen == "true") {
				$(b.element).prepend("<i class='ion-ios-minus-empty'></i>")
			} else {
				$(b.element).prepend("<i class='ion-ios-browsers-outline'></i>")
			}
			$(b.element).on("click", function() {
				if (b.isOpen == "true") {
					$(b.element).find("i").attr("class", "ion-ios-browsers-outline");
					b.isOpen = "false"
				} else {
					$(b.element).find("i").attr("class", "ion-ios-minus-empty");
					b.isOpen = "true"
				}
			})
		};
		return b
	};
	a.button.close = function(c) {
		var b = a.button.call(this, c);
		b.create = function() {
			if (b.container && b.option) {
				b.createElement()
			}
			a.setProperty(b);
			return b
		};
		return b
	};
	a.getCheckbox = function(c) {
		var b = [];
		var b = $("input[name='" + c + "']:checked");
		return b
	};
	a.carousel = function(c) {
		var b = {};
		if (!c) {
			return
		} else {
			b.option = c.option ? c.option : null;
			b.container = $(c.container) ? $(c.container) : null;
			if (!b.option || !b.container) {
				return
			}
			b.index = 0;
			b.data = b.option.data ? b.option.data : null;
			b.remoteData = b.option.remoteData ? b.option.remoteData : null;
			b.step = b.option.step ? b.option.step : 4;
			b.interval = b.option.interval ? b.option.interval : false;
			b.carouselBody = $(b.container).find(".carousel-inner")
		}
		b.setCarousel = function() {
			if (b.option.setCarouselCallback) {
				b.option.setCarouselCallback(b)
			}
		};
		b.next = function() {
			$(b.container).carousel("next")
		};
		b.prev = function() {
			$(b.container).carousel("prev")
		};
		b.drawCarousel = function() {
			if (b.option.slideCallback) {
				$(b.container).on("slide.bs.carousel", function() {
					b.option.slideCallback(b)
				})
			}
			if (b.data) {
				b.setCarousel();
				$(b.container).carousel({
					interval: b.interval
				})
			}
		};
		b.create = function() {
			a.setProperty(b);
			b.drawCarousel();
			b.setCarousel();
			return b
		};
		return b
	};
	a.progress = function(c) {
		var b = {};
		b.element = null;
		b.option = c.option ? c.option : null;
		b.container = c.container ? c.container : null;
		b.data = Number(b.option.data) ? Number(b.option.data) : 0;
		b.animate = b.option.animate ? b.option.animate : true;
		b.progressColor = b.option.progressColor ? b.option.progressColor : "#0298f7";
		b.bgColor = b.option.bgColor ? b.option.bgColor : "#222";
		b.radius = b.option.radius ? b.option.radius : 5;
		b.label = null;
		b.height = b.option.height ? b.option.height : 9;
		b.progress = null;
		b.labelColor = b.option.labelColor ? b.option.labelColor : "#fff";
		b.progressLine = null;
		b.create = function() {
			$(b.container).html("");
			if (!b.option.label) {
				b.setLabel()
			} else {
				if (b.option.label.show || b.option.label.show == "undefined") {
					b.setLabel()
				}
			}
			b.setProgress();
			a.setProperty(b);
			return b
		};
		b.setLabel = function() {
			b.label = document.createElement("div");
			$(b.label).css("text-align", "center");
			$(b.label).css("padding-top", "3px");
			$(b.label).css("position", "relative");
			$(b.label).css("z-index", 3);
			$(b.label).css("top", 3);
			$(b.label).css("color", b.labelColor);
			$(b.label).css("font-size", "10px");
			$(b.label).html(b.data + "%");
			$(b.container).append(b.label)
		};
		b.setProgress = function() {
			b.progress = document.createElement("div");
			$(b.progress).css("border-radius", b.radius);
			$(b.progress).css("width", "100%");
			$(b.progress).css("position", "relative");
			$(b.progress).css("z-index", 1);
			$(b.progress).css("top", -14);
			$(b.progress).css("background", b.bgColor);
			$(b.progress).css("padding", 0);
			$(b.progress).css("height", b.height);
			$(b.progress).css("font-size", "13px");
			$(b.container).append(b.progress);
			b.setProgressLine()
		};
		b.animate = function() {
			if (b.animate){
				$(b.progressLine).css("width", 0);
				$(b.progressLine).animate({
					width: $(b.progress).width() * (b.data / 100) + "px"
				}, 1500)
			} else {
				$(b.progressLine).css("width", $(b.progress).width() * (b.data / 100) + "px")
			}
		};
		b.setProgressLine = function() {
			b.progressLine = document.createElement("div");
			$(b.progressLine).css("border-radius", b.radius);
			if (jQuery.isArray(b.progressColor)) {
				var g = null;
				var f = 0;
				var e = 0;
				$(b.progressColor).each(function(d, h) {
					if (h.split(":").length > 1) {
						f = e;
						e = h.split(":")[1]
					} else {
						f = e;
						e = Math.round(100 / b.progressColor.length * (d + 1))
					}
					if (b.data >= f && b.data <= e) {
						g = h
					}
				});
				if (g) {
					$(b.progressLine).css("background", g.split(":")[0])
				}
			} else {
				$(b.progressLine).css("background", b.progressColor)
			}
			$(b.progressLine).css("padding", 0);
			$(b.progressLine).css("height", "100%");
			$(b.progress).append(b.progressLine);
			b.animate()
		};
		return b
	};
	a.roundLoader = function(c) {
		var b = {};
		b.theadThs = c.theadThs ? c.theadThs : null;
		b.container = c.container ? c.container : null;
		b.time = b.option.time ? b.option.time : null;
		b.showPercentage = b.option.showPercentage ? b.option.showPercentage : false;
		b.element = null;
		b.value = b.value ? b.value : 0;
		b.create = function() {
			if (b.option && b.container) {
				require(["radialIndicator"], function() {
					a.setProperty(b);
					b.set()
				})
			}
			return b
		};
		b.set = function() {
			if (b.time) {
				window.setInterval(function() {
					if (b.value < 100) {
						b.value = b.value + 1
					} else {
						b.value = 0;
						if (b.option.drawCallback) {
							b.option.drawCallback(b)
						}
					}
					b.draw()
				}, b.time / 100)
			} else {
				b.draw()
			}
		};
		b.draw = function() {
			$(b.container).html("");
			b.element = $(b.container).radialIndicator({
				radius: 12,
				showPercentage: b.showPercentage,
				displayNumber: false,
				barWidth: 2.5,
				roundCorner: true,
				barBgColor: "rgba(0,0,0,0)",
				barColor: "#20c6fc",
				shadowColor: "#20c6fc",
				shadowRadius: 5
			}).data("radialIndicator");
			b.element.value(b.value)
		};
		return b
	};
	a.echarts = function(c) {
		var b = {};
		b.container = null;
		b.option = c.option;
		b.dataOption = null;
		b.parentOption = c.parentOption;
		b.chart = null;
		b.data = null;
		b.echarts = null;
		b.theme = null;
		b.chartEle = null;
		b.option.seriesType = b.option.seriesType ? b.option.seriesType : "map";
		b.element = document.createElement("div");
		$(b.element).addClass("echarts-chart");
		a.setProperty(b);
		if (a.isFalse(b.option.show)) {
			$(b.element).hide()
		} else {
			$(b.element).show()
		}
		$(c.container).append(b.element);
		b.mapSelectFuc = function(d) {
			if (b.option.seriesType == "map") {
				d.on("mapselectchanged", function(e) {
					a.$scope[b.option.mapSelectedCallback](e, b)
				})
			} else {
				d.on("click", function(e) {
					a.$scope[b.option.mapSelectedCallback](e, b)
				})
			}
		};
		b.clickFuc = function(d) {
			require(["echartsConfig"], function(e) {
				d.on("click", function(f) {
					a.$scope[b.option.clickCallback](f, b)
				})
			})
		};
		b.hoverFuc = function(d) {
			require(["echartsConfig"], function(e) {
				d.on("hover", function(f) {
					a.$scope[b.option.hoverCallback](f)
				})
			})
		};
		b.create = function() {
			if (b.option.option) {
				b.option.dataOption = a.val(b.option.option);
				if (b.option.setOptionDataCallback) {
					a.$scope[b.option.setOptionDataCallback](b.option)
				}
				if (b.option.type == "map") {
					require(["echarts2.0"], function() {
						if (b.option.mapData && b.option.mapName) {
							echarts.util.mapData.params.params[b.option.mapName] = {
								getGeoJson: function(d) {
									if (typeof(b.option.mapData) == "string") {
										$.getJSON(b.option.mapData, function(e) {
											d(echarts.util.mapData.params.decode(e))
										})
									} else {
										d(echarts.util.mapData.params.decode(b.option.mapData))
									}
								}
							};
							b.echarts = echarts;
							b.chart = b.echarts.init(b.element, b.theme);
							b.chartEle = b.chart.setOption(b.option.dataOption, true);
							$(window).on("resize", function() {
								b.reDraw()
							});
							if (b.option.mapSelectedCallback) {
								b.mapSelectFuc(b.chart)
							}
							if (b.option.clickCallback) {
								b.clickFuc(b.chart)
							}
							if (b.option.hoverCallback) {
								b.hoverFuc(b.chart)
							}
						} else {
							b.echarts = echarts;
							b.chart = b.echarts.init(b.element, b.theme);
							b.chartEle = b.chart.setOption(b.option.dataOption, true);
							$(window).on("resize", function() {
								b.reDraw()
							});
							if (b.option.mapSelectedCallback) {
								b.mapSelectFuc(b.chart)
							}
							if (b.option.clickCallback) {
								b.clickFuc(b.chart)
							}
							if (b.option.hoverCallback) {
								b.hoverFuc(b.chart)
							}
						}
					})
				} else {
					require(["echarts.min", b.option.theme], function(d, e) {
						b.echarts = d;
						b.theme = e;
						b.chart = b.echarts.init(b.element, b.theme);
						b.chartEle = b.chart.setOption(b.option.dataOption, true);
						$(window).on("resize", function() {
							b.reDraw()
						});
						if (b.option.mapSelectedCallback) {
							b.mapSelectFuc(b.chart)
						}
						if (b.option.clickCallback) {
							b.clickFuc(b.chart)
						}
						if (b.option.hoverCallback) {
							b.hoverFuc(b.chart)
						}
					})
				}
			}
		};
		b.setChartOption = function(d) {
			b.option.dataOption = d
		};
		b.getChartOption = function() {
			return b.option.dataOption
		};
		b.refresh = function(d) {
			b.setChartOption(d);
			b.chart.setOption(b.option.dataOption, true)
		};
		b.reDraw = function() {
			b.chart = b.echarts.init(b.element, b.theme);
			b.chart.setOption(b.option.dataOption, true);
			if (b.option.mapSelectedCallback) {
				b.mapSelectFuc(b.chart)
			}
			if (b.option.entityId) {
				a.$scope.entities[b.option.entityId] = b
			}
		};
		b.create();
		return b
	};
	a.isFalse = function(c) {
		var b = true;
		if (c == undefined) {
			b = false
		} else {
			if (c) {
				b = false
			} else {
				b = true
			}
		}
		return b
	};
	a.highCharts = function(c) {
		var b = {};
		b.container = c.container ? c.container : null;
		b.option = c.option ? c.option : null;
		b.chartOption = b.option.chartOption ? b.option.chartOption : null;
		b.theme = b.option.theme ? b.option.theme : "dark";
		b.element = null;
		b.isMain = b.option.isMain ? b.option.isMain : true;
		b.create = function() {
			if (b.container && b.chartOption) {
				if (b.isMain) {
					b.element = b.container
				} else {
					b.element = document.createElement("div");
					$(b.container).append(b.element)
				}
				a.setProperty(b);
				$(b.element).highcharts(b.chartOption)
			}
			return b
		};
		return b
	};
	a.spreader = function(c) {
		var b = {};
		b.container = c.container ? c.container : null;
		b.option = c.option ? c.option : null;
		b.data = b.option.data ? b.option.data : null;
		b.step = b.option.step ? b.option.step : 0;
		b.mapOption = b.option.mapOption ? b.option.mapOption : null;
		b.radius = b.option.radius ? b.option.radius : 0.05;
		b.offset = b.option.offset ? b.option.offset : 10;
		b.origin = b.option.origin ? b.option.origin : [0, 0];
		b.mapData = {
			type: "FeatureCollection",
			features: [],
			UTF8Encoding: true
		};
		b.markLineData = [];
		b.markPointData = [];
		b.geoCoord = {};
		b.originId = null;
		b.element = null;
		b.isMain = b.option.isMain ? b.option.isMain : true;
		b.create = function() {
			if (b.data && b.data.length > 0) {
				if (b.isMain) {
					b.element = b.container
				} else {
					b.element = document.createElement("div");
					$(b.container).append(b.element)
				}
				b.setData(b.data);
				b.draw()
			}
			return b
		};
		b.draw = function() {
			if (b.mapOption) {
				var d = a.echarts({
					container: b.element,
					option: {
						type: "map",
						style: "width:100%;height:100%",
						option: b.mapOption,
						mapData: b.mapData,
						mapName: "service",
						clickCallback: b.option.clickCallback ? b.option.clickCallback : null,
						hoverCallback: b.option.hoverCallback ? b.option.hoverCallback : null
					}
				})
			} else {
				a.console("无地图配置")
			}
		};
		b.getPosition = function(e, j, i, d) {
			if (!e || !j || !i) {
				return []
			}
			var d = d ? d : b.radius;
			var f = (Number(e) + Math.sin(2 * Math.PI / 360 * i) * d);
			var g = (Number(j) + Math.cos(2 * Math.PI / 360 * i) * d);
			var h = [f, g];
			return h
		};
		b.setData = function(e, d) {
			$(e).each(function(l, g) {
				var f = g.id;
				var n = [];
				var h = 0;
				var m = 0;
				if (d) {
					if (d.cp[0] == b.origin[0] && d.cp[1] == b.origin[1]) {
						h = b.step > 0 ? (360 / b.step) * (l + 1) : (360 / e.length) * (l + 1);
						m = b.radius
					} else {
						var j = d.angle;
						h = j / 1.5 + (10) * (l + 1);
						m = b.radius
					}
					n = b.getPosition(d.cp[0], d.cp[1], h, m)
				} else {
					n = b.origin
				}
				if (b.originId) {
					b.originId = f
				}
				b.geoCoord[g.name] = n;
				if (d) {
					b.markLineData.push([{
						name: g.name
					}, {
						name: d.data.name,
						value: g.value
					}]);
					b.markPointData.push({
						name: g.name,
						value: g.value
					})
				} else {
					b.markLineData.push([{
						name: g.name
					}, {
						name: b.data[0].name,
						value: g.value
					}])
				}
				var p = {
					type: "Feature",
					id: f,
					properties: {
						name: g.name,
						cp: n,
						childNum: 1
					},
					geometry: {
						type: "Polygon",
						coordinates: ["@@DB@DF@@@B@@@@@@@B@B@@@@@@@@@@@D@BAB@@@B@B@@@B@B@BA@@B@B@@BD@@F@B@@@B@@@@@B@B@B@@@B@@@@@@@FD@@CA@@AD@BC@@@A@EB@@CD@@AB@B@R@@@@@@@@@@@@ED@@@@@D@@@@@B@@E@@@BA@@A@@@A@@@A@@@@@@@@@@@A@@@CB@@@@@@@@@@AA@@@@@@@@AF@B@@@B@@@@@@@@@@@@@@AB@@BD@@@@@@@@@A@@@@B@@AB@@@@B@@@@@FBBA@CD@@EAA@@@A@@@@@@B@@@@@@@@@@@BB@@@@B@@@@@@@B@D@@@@@@@@@@@@AB@@@A@@@@A@A@@@@@@@@A@@@AA@@@A@@@A@A@@@@@AA@@A@@@E@@@@@@@A@C@@@@@@@@@@@E@@@@CB@@@@A@@@@@A@AA@@@@@@@@@BA@@@A@@A@@@BE@@IBE@G@C@C@@@@BC@A@@@C@C@A@C@A@A@@B@@@@BB@@@B@B@@@B@B@@@B@B@B@B@B@@@D@@CA@C@@AB@@@@@@@@C@@@A@@@EH@@AD@@@@@@@@AC@@C@@A@A@AB@@@@A@@@A@@AC@@ABG@A@@@@BE@C@A@@@@@AAAAA@@@A@C@@@@@@AA@@@@@@C@KBO@C@@@C@A@@@A@AAA@@@E@@@E@@CD@@@@@@A@@@@@C@@@@B@@AA@@C@@@@@@A@@@A@BGA@@@@@A@@A@@@@@@C@C@CB@@@@C@@@A@@@A@A@@@@@A@@@@@A@@AI@@@B@A@@B@B@B@@@@@@@B@@A@@@@A@@AB@BA@A@@@@@@DE@@@@@@B@@@BEA@@A@A@AAA@A@@@@@A@@B@@@@A@@@@@A@@@@@@@@@B@@B@@@@B@@@@@@@@@B@@D@@@DJ@B@D@@@@@H@NB@B@B@@@L@@@B@@@BAH@J@J@F@@@@@@A@@@A@@@@B@@@@@D@B@JG@@BB@@B@@@@@DA@@@@@@B@@@@B@AD@@@@@B@NJ@@B@@@@@@@@@@@@F@AJ@@@@C@AX@@@@@@AB@@A@@F@N@NMA@F@B"],
						encodeOffsets: [
							[119181, 40920]
						]
					}
				};
				b.mapData.features.push(p);
				if (g.items && g.items.length > 0) {
					var k = {
						cp: n,
						data: g,
						angle: h
					};
					b.setData(g.items, k)
				}
			});
			b.mapOption.series[0].geoCoord = b.geoCoord;
			b.mapOption.series[0].markLine.data = b.markLineData;
			b.mapOption.series[0].markPoint.data = b.markPointData
		};
		return b
	};
	a.modal = function(c) {
		if (!c) {
			c = {}
		}
		var b = {};
		b.container = c.container ? c.container : $("body");
		b.option = c.option ? c.option : {};
		b.modalId = b.option.id ? b.option.id : "m" + a.getRandom(1000, 9999);
		b.label = b.option.label ? b.option.label : "新窗口";
		b.labelColor = b.option.labelColor ? b.option.labelColor : "#222";
		b.background = b.option.background ? b.option.background : "#fff";
		b.modalLabelId = b.option.id + "label";
		b.style = b.option.style ? b.option.style : "";
		b.size = b.option.size ? b.option.size : null;
		b.hide = b.option.hide ? b.option.hide : false;
		b.center = b.option.center ? b.option.center : true;
		b.drag = b.option.drag ? b.option.drag : false;
		b.modalEntity = {};
		b.modal = null;
		b.element = null;
		b.modalDialog = null;
		b.modalContent = null;
		b.modalHeader = null;
		b.modalFooter = null;
		b.create = function() {
			if ($("#" + b.modalId).length > 0) {
				$("#" + b.modalId).modal("show")
			} else {
				b.createModal()
			}
			return b
		};
		b.createModal = function() {
			b.modal = document.createElement("div");
			$(b.modal).attr("id", b.modalId);
			$(b.modal).addClass("modal fade");
			$(b.container).append(b.modal);
			$(b.modal).attr("role", "dialog");
			$(b.modal).attr("tabindex", "-1");
			$(b.modal).attr("aria-labelledby", b.modalId + "Label");
			if (b.hide) {
				$(b.modal).attr("aria-hidden", "true")
			} else {
				$(b.modal).attr("aria-hidden", "false")
			}
			var d = {};
			d.element = b.modal;
			d.option = {};
			d.option.id = b.modalId;
			d.option.cls = b.option.cls;
			a.setProperty(d);
			b.createModalDialog();
			if (!b.hide) {
				$(b.modal).modal("show")
			}
		};
		b.createModalDialog = function() {
			b.modalDialog = document.createElement("div");
			$(b.modalDialog).addClass("modal-dialog");
			$(b.modal).append(b.modalDialog);
			if (b.size) {
				$(b.modalDialog).css("width", b.size)
			}
			b.createModalContent()
		};
		b.createModalContent = function() {
			b.modalContent = document.createElement("div");
			$(b.modalContent).addClass("modal-content");
			$(b.modalDialog).append(b.modalContent);
			b.createModalHeader();
			b.createModalBody();
			b.createModalFooter()
		};
		b.createModalHeader = function() {
			b.modalHeader = document.createElement("div");
			$(b.modalHeader).addClass("modal-header");
			$(b.modalContent).append(b.modalHeader);
			if (b.background) {
				$(b.modalHeader).css("background", b.background)
			}
			if (b.drag) {
				$(b.modalDialog).draggable({
					handle: ".modal-header",
					cursor: "move",
					refreshPositions: false
				})
			}
			a.render({
				container: b.modalHeader,
				option: {
					items: [{
						type: "button",
						cls: "close",
						attr: "data-dismiss::modal,aria-hidden::true",
						text: "X"
					}, {
						type: "custom",
						customType: "h4",
						id: b.modalId + "Label",
						text: b.label,
						style: "color:" + b.labelColor,
						cls: "modal-title"
					}]
				}
			})
		};
		b.createModalBody = function() {
			b.element = document.createElement("div");
			$(b.element).addClass("modal-body");
			$(b.modalContent).append(b.element);
			var d = {};
			d.element = b.element;
			d.option = {};
			d.option.html = b.option.html;
			a.setProperty(d)
		};
		b.createModalFooter = function() {
			b.modalFooter = document.createElement("div");
			$(b.modalFooter).addClass("modal-footer");
			$(b.modalContent).append(b.modalFooter);
			if (b.option.buttons && b.option.buttons.length > 0) {
				$(b.option.buttons).each(function(d, e) {
					b.createModalButton(e)
				})
			}
		};
		b.show = function() {
			$(b.modal).modal("show")
		};
		b.hide = function() {
			$(b.modal).modal("hide")
		};
		b.createModalButton = function(d) {
			switch (d.type) {
			case "button.close":
				d.text = "关闭";
				d.clickCallback = function() {
					b.hide()
				};
				a.button.close({
					container: b.modalFooter,
					option: d
				}).create();
				break;
			default:
				a.button({
					container: b.modalFooter,
					option: d
				}).create();
				break
			}
		};
		return b
	};
	a.msg = function(c) {
		if (!c) {
			c = {}
		}
		var b = {};
		b.container = c.container ? c.container : $("body");
		b.option = c.option ? c.option : {};
		b.msgId = b.option.id ? b.option.id : "msg" + a.getRandom(1000, 9999);
		b.width = b.option.width ? b.option.width : 200;
		b.height = b.option.height ? b.option.height : 150;
		b.background = b.option.background ? b.option.background : "#025daa";
		b.msg = null;
		b.msgHeader = null;
		b.msgHeaderLabel = null;
		b.msgHeaderButtonbar = null;
		b.element = null;
		b.minBtn = null;
		b.min = b.option.min ? b.option.min : true;
		b.closeBtn = null;
		b.animate = b.option.animate ? b.option.animate : "slide";
		b.drag = b.option.drag ? b.option.drag : false;
		b.position = b.option.position ? b.option.position : "right";
		b.create = function() {
			var d = b.findEntity();
			if (!d) {
				b.createMsg()
			}
			return b
		};
		b.findEntity = function() {
			return a.msgGroup[b.msgId] ? a.msgGroup[b.msgId] : null
		};
		b.createMsg = function() {
			b.msg = document.createElement("div");
			$(b.msg).addClass("msg-box");
			$(b.msg).css("width", b.width);
			if (b.position == "left") {
				$(b.msg).css("left", 1)
			} else {
				$(b.msg).css("right", 1)
			}
			$(b.msg).css("bottom", b.height);
			$(b.container).append(b.msg);
			var d = {};
			d.element = b.msg;
			d.option = {};
			d.option.id = b.msgId;
			d.option.style = b.option.style;
			d.option.cls = b.option.cls;
			a.setProperty(d);
			b.createMsgHeader();
			b.createMsgContent();
			a.msgGroup[b.msgId] = b;
			b.show()
		};
		b.createMsgHeader = function() {
			b.msgHeader = document.createElement("div");
			$(b.msgHeader).addClass("msg-header");
			$(b.msgHeader).attr("onselectstart", "return false");
			$(b.msgHeader).css("-moz-user-select", "none");
			$(b.msg).append(b.msgHeader);
			if (b.drag) {
				$(b.msg).draggable({
					handle: ".msg-header",
					cursor: "move",
					refreshPositions: false
				})
			}
			if (b.option.labelColor) {
				$(b.msgHeader).css("color", b.option.labelColor)
			}
			$(b.msgHeader).css("background", b.background);
			$(b.msgHeader).on("dblclick", function() {
				if ($(this).find("i").hasClass("ion-ios-minus-empty")) {
					b.hideContent($(this))
				} else {
					b.showContent($(this))
				}
			});
			b.createMsgHeaderLabel();
			b.createMsgHeaderButton()
		};
		b.createMsgHeaderLabel = function() {
			b.msgHeaderLabel = document.createElement("div");
			$(b.msgHeaderLabel).addClass("msg-header-label");
			if (b.option.label) {
				$(b.msgHeaderLabel).html(b.option.label)
			}
			$(b.msgHeader).append(b.msgHeaderLabel)
		};
		b.createMsgHeaderButton = function() {
			b.msgHeaderButtonbar = document.createElement("div");
			$(b.msgHeaderButtonbar).addClass("msg-header-buttonbar");
			if (b.min) {
				b.minBtn = document.createElement("button");
				$(b.minBtn).html("<i class='ion-ios-minus-empty'></i>");
				$(b.msgHeaderButtonbar).append(b.minBtn);
				$(b.minBtn).on("click", function() {
					if ($(this).find("i").hasClass("ion-ios-minus-empty")) {
						b.hideContent($(this))
					} else {
						b.showContent($(this))
					}
				})
			}
			b.closeBtn = document.createElement("button");
			$(b.closeBtn).html("<i class='ion-android-close'></i>");
			$(b.msgHeaderButtonbar).append(b.closeBtn);
			$(b.msgHeader).append(b.msgHeaderButtonbar);
			$(b.closeBtn).on("click", function() {
				b.close()
			})
		};
		b.createMsgContent = function() {
			b.element = document.createElement("div");
			$(b.element).addClass("msg-body");
			var d = {};
			d.element = b.element;
			d.option = {};
			d.option.html = b.option.html;
			a.setProperty(d);
			$(b.element).css("height", b.height - 30);
			$(b.msg).append(b.element)
		};
		b.show = function() {
			var d = b.findEntity();
			if (d) {
				$(d.msg).show();
				d.animateHandle(d, "up")
			}
		};
		b.animateHandle = function(d, e) {
			if (d.animate) {
				switch (d.animate) {
				case "fade":
					if (e == "up") {
						$(d.msg).css("opacity", 0);
						$(d.msg).css("bottom", -d.height);
						$(d.msg).animate({
							opacity: 1,
							bottom: 0
						}, 1000)
					} else {
						$(d.msg).css("opacity", 1);
						$(d.msg).animate({
							opacity: 0,
							bottom: -d.height
						}, 1000)
					}
					break;
				case "slide":
					if (e == "up") {
						$(d.msg).css("bottom", -d.height);
						$(d.msg).animate({
							bottom: 0
						}, 500)
					} else {
						$(d.msg).animate({
							bottom: -d.height
						}, 500)
					}
					break
				}
			}
		};
		b.showContent = function() {
			var d = b.findEntity();
			if (d) {
				$(d.msg).css("height", "auto");
				$(d.element).show();
				$(d.minBtn).find("i").attr("class", "ion-ios-minus-empty")
			}
		};
		b.hideContent = function() {
			var d = b.findEntity();
			if (d) {
				$(d.msg).css("height", "auto");
				$(d.element).hide();
				$(d.minBtn).find("i").attr("class", "ion-android-checkbox-outline-blank")
			}
		};
		b.close = function() {
			var d = b.findEntity();
			if (d) {
				$(d.msg).remove();
				delete a.msgGroup[b.msgId]
			}
		};
		return b
	};
	return a
});