/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.fn.api.settings.api = {
    'create customer': '/MyStore-war/CustomerController',
    'create product': '/MyStore-war/ProductController',
    'get products': '/MyStore-war/ProductController'
};

$(".menu a.item").bind('click', function () {
    var menu = $(this).attr("data-value");
    $(".menu a.item").removeClass("active");
    $(this).addClass("active");
    switch (menu)
    {
        case "Product":
            $("#producBody").show();
            $("#customerEnrollBody").hide();
            break;
        case "CustomerEnroll":
            $("#customerEnrollBody").show();
            $("#producBody").hide();
            break;
        default:
            $("#customerEnrollBody").hide();
            $("#producBody").hide();
    }
});
$("#productForm,#customerEnrollBody").form({
    on: 'blur',
    fields: {
        productName: {
            identifier: 'productName',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Product name missing'
                }
            ]
        },
        productDesc: {
            identifier: 'productDesc',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Product description empty'
                }
            ]
        },
        productQty: {
            identifier: 'productQty',
            rules: [
                {
                    type: 'integer[1...]',
                    prompt: 'Product quantity invalid'
                }
            ]
        },
        productPrice: {
            identifier: 'productPrice',
            rules: [
                {
                    type: 'decimal',
                    prompt: 'Product price invalid'
                }
            ]
        },
        firstName: {
            rules: [
                {
                    type: 'empty',
                    prompt: 'Customer name cannot be empty'
                }
            ]
        },
        lastName: {
            rules: [
                {
                    type: 'empty',
                    prompt: 'Customer last name cannot be empty'
                }
            ]
        }
    }
});
function doFormSubmit($form) {
    var id = $form.attr("id");
    switch (id) {
        case"customerForm":
            var data = $form.form('get values', ['firstName', 'lastName']);
            data.action = "create";
            $(this).api({
                action: 'create customer',
                on: 'now',
                method: 'POST',
                data: data,
                onComplete: function (response) {
                    // always called after XHR complete
                    $form.form("reset");
                }
            });
            break;
        case"productForm":
            var data = $form.form('get values', ['productName', 'productDesc', 'productQty', 'productPrice']);
            data.action = "create";
            $(this).api({
                action: 'create product',
                on: 'now',
                method: 'POST',
                data: data,
                onComplete: function (response) {
                    // always called after XHR complete
                    $form.form("reset");
                }
            });
            break;
        default:
            console.log("");
    }
    console.log(data);
    return false;
}
function getAllProducts() {
    $(this).api({
        action: 'get products',
        on: 'now',
        method: 'GET',
        data: {
            action: "get products"
        },
        onResponse: function (response) {
            // make some adjustments to response
            return response;
        },
        successTest: function (response) {
            // test whether a JSON response is valid
            return response.status || false;
        },
        onSuccess: function (response) {
            // valid response and response.success = true
            if(response.products.length > 0){
                var html = '';
                $.each(response.products,function(i, item){
                    html += '<tr>';
                    html += '<td>'+item.name+'</td>';
                    html += '<td>'+item.description+'</td>';
                    html += '<td>'+item.quantity+'</td>';
                    html += '<td>'+item.price+'</td>';
                    html +='</tr>';
                });
                $("#productListBody").html(html);
                $("#productList").show();
            }
        },
        onComplete: function (response) {
            // always called after XHR complete
        }
    });
}
getAllProducts();