//Khi trang web load xong thì sẽ gọi hàm ready
$(document).ready(function () {
    //$ ==> dùng thư viện jquery
    //Đăng ký sự kiện change(thay đổi) số lượng sản phẩm trên giỏ hàng
    $('.quantity').change(function () {
        //Lấy về số lượng người dùng nhập vao, thay đổi
        let quantity = $(this).val();
        //Lấy về cái id của quyển sách với trường data-id nằm trong thẻ input
        let id = $(this).attr('data-id');
        //Gọi Ajax lên link để tiến hành update dữ liệu
        $.ajax({
            //link gọi /cart/updatecart/id/quantity
            url: '/cart/updatecart/' + id + '/' + quantity,
            //Phương thức gọi là GET
            type: 'GET',
            success: function () {
                //Tiến hành reload lại toàn bộ trang hiện tại
                location.reload();
            }
        });
    });
})