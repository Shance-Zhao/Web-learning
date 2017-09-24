(function ($) {
    $.fn.order = function (setting) {
            //initial configuration
            var opts = $.extend({}, $.fn.order.defaults, setting);
            //read cookies info
            var initdata = {};
            if (opts.savecookie && $.cookie(opts.cookiename) != null && $.cookie(opts.cookiename) != '') {
                initdata = eval('(' + $.cookie(opts.cookiename) + ')');
            }
            //initial cart
            $("body").data(opts.staticname, initdata);
            //Initial page
            var order = $('<div><div>' + opts.leftdemo + '</div><div><ul><li style="text-align:center">' + opts.nomessage + '</li></ul><span id="totalmoney"></span><div></div></div></div>').attr('class', opts.orderclass).appendTo($("body"));
        //eq:match the element div value to the specified index
            var orderright = order.find('div:eq(1)').attr('class', 'right');
            var orderleft = order.find('div:eq(0)').attr('class', 'left').click(function () {
                orderright.toggle();
            });
            var slide = {
                //calculate price and quantity
                info: function (price, count) {
                    return "$" + price * 10000 * count / 10000 + "(Quantity:" + count + ")";
                }
                , //add an order item
                addorder: function (e) {
                    var orderdata = $("body").data(opts.staticname);
                    var id = $(this).attr('id');
                    var name = $(this).attr(opts.namefiled);
                    var price = $(this).attr(opts.pricefiled);
                    if (orderdata[id] == null || orderdata[id]['count'] == 0) {
                        if (orderdata[id] == null) {
                            orderdata[id] = {};
                        }
                        orderdata[id]['count'] = 1;
                        orderdata[id]['name'] = name;
                        orderdata[id]['price'] = price;
                        $("div." + opts.orderclass + " ul").append("<li id='" + opts.orderpre + id + "'><span>" + name + "<br><b>" + slide.info(price, orderdata[id]['count']) + "</b><span  class='del'></span><span  class='sub'></span><span class='add'></span></span></li>");
                        $("#" + opts.orderpre + id + " span.add").click(function () {
                            slide.addordernum(name, id, price);
                        });
                        $("#" + opts.orderpre + id + " span.sub").click(function () {
                            slide.delordernum(name, id, price);
                        });
                        $("#" + opts.orderpre + id + " span.del").click(function () {
                            slide.delorder(id);
                        });
                    }
                    else {
                        orderdata[id]['count'] += 1;
                        $("#" + opts.orderpre + id + " b").html(slide.info(price, orderdata[id]['count']));
                    }
                    slide.refresh();
                }
                , //delete an order item
                delorder: function (id) {
                    var orderdata = $("body").data(opts.staticname);
                    orderdata[id]['count'] = 0;
                    $("#" + opts.orderpre + id).remove();
                    slide.refresh();
                }
                , //+ an order item number
                addordernum: function (name, id, price) {
                    var orderdata = $("body").data(opts.staticname);
                    orderdata[id]['count'] += 1;
                    $("#" + opts.orderpre + id + " b").html(slide.info(price, orderdata[id]['count']));
                    slide.refresh();
                }
                , //- an order item number
                delordernum: function (name, id, price) {
                    var orderdata = $("body").data(opts.staticname);
                    orderdata[id]['count'] -= 1;
                    if (orderdata[id]['count'] > 0) {
                        $("#" + opts.orderpre + id + " b").html(slide.info(price, orderdata[id]['count']));
                    }
                    else {
                        $("#" + opts.orderpre + id).remove();
                    }
                    slide.refresh();
                }
                , //submit
                subm: function () {
                    opts.dosubmit($("body").data(opts.staticname));
                    $("body").data(opts.staticname, {});
                    //eq:match the element div value to the specified index
                    $("div." + opts.orderclass + " ul li:eq(0)").show();
                    //gt:choose all elements after the 0
                    $("div." + opts.orderclass + " ul li:gt(0)").remove();
                    $('div.' + opts.orderclass + ' a.button').remove();
                    if (opts.savecookie) {
                        var date = new Date();
                        date.setTime(date.getTime() - (1 * 24 * 60 * 60));//one day,24hours,60min,60sec
                /*create a new cookie inclues expiration time, path,  
                so when submit and refresh the page, the cart will clear*/
                        $.cookie(opts.cookiename, '', { path: '/', expires: date });   
                    }
                }
                , //refresh cart
                refresh: function () {
                    orderright.show();
                    var data = $("body").data(opts.staticname);
                    var size = 0;
                    var totalmoney = 0;
                    for (var i in data) {
                        if (data[i]['count'] != 0) {
                            totalmoney = totalmoney + data[i]['count'] * parseFloat(data[i]['price']);
                            size++;
                        }
                        else {
                            delete data[i];
                        }
                    }
                    $("#totalmoney").html("Total:" + totalmoney);
                    if (size > 0) {
                        $("div." + opts.orderclass + " ul li:eq(0)").hide();
                      //  if ($('div.' + opts.orderclass + ' a.button').size() == 0) $('<a class="button">' + opts.subbuttom + '</a>').appendTo(orderright).click(slide.subm);
                        if ($('div.' + opts.orderclass + ' a.button').size() == 0) $('<a class="button">' + opts.subbuttom + '</a>').appendTo(orderright).click(slide.subm);
                    }
                    else {
                        $("div." + opts.orderclass + " ul li:eq(0)").show();
                        $('div.' + opts.orderclass + ' a.button').remove();
                    }
                    if (opts.savecookie) {
                        var date = new Date();
                        date.setTime(date.getTime() + (1 * 24 * 60 * 60 * 1000));
                        $.cookie(opts.cookiename, JSON.stringify(data), {
                            path: '/', expires: 0
                        });
                    }
                }
            };
            //initial cart
            var data = $("body").data(opts.staticname);
            var totalmoney = 0;
            for (var id in data) {
                totalmoney = totalmoney + data[id]['count'] * parseInt(data[id]['price']);
                $("div." + opts.orderclass + " ul").append("<li id='" + opts.orderpre + id + "'><span>" + data[id]['name'] + "<br><b>" + slide.info(data[id]['price'], data[id]['count']) + "</b><span  class='del'></span><span  class='sub'></span><span class='add'></span></span></li>");
                $("#" + opts.orderpre + id + " span.add").data('dd', id).click(function () {
                    var d = $(this).data('dd');
                    slide.addordernum(data[d]['name'], d, data[d]['price']);
                });
                $("#" + opts.orderpre + id + " span.sub").data('dd', id).click(function () {
                    var d = $(this).data('dd');
                    slide.delordernum(data[d]['name'], d, data[d]['price']);
                });
                $("#" + opts.orderpre + id + " span.del").data('dd', id).click(function () {
                    var d = $(this).data('dd');
                    slide.delorder(d);
                });
                slide.refresh();
            }
            $("#totalmoney").html(totalmoney);
            $(opts.addbuttom).click(slide.addorder);
            return order;
        }
        //configuration
    $.fn.order.defaults = {
        //Global data-save order info
        staticname: 'order'
        , //Order class
        orderclass: 'order'
        , //whether save cookies
        savecookie: true
        , //cookie's name
        cookiename: 'order'
        , //ID Prefix
        numpre: 'no_'
        , 
        orderpre: 'order_'
        , //price
        pricefiled: 'price'
        , //order list's name
        namefiled: 'ordername'
        , //CART
        leftdemo: 'C A R T'
        , //submit icon
        subbuttom: ''
        , //control selection by default adding orders
        addbuttom: 'a.orderadd'
        , //when don't have an order
        nomessage: 'Nothing'
        , //Triggered when it commits
        dosubmit: function (data) {
            $("body").append(JSON.stringify(data));
            $("body").data(opts.staticname, {});
        }
    };
})(jQuery);