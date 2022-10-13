/**
 * 결제시스템
 */
 $(function(){
 $("#btn-payment").click(requestPay);
});

function requestPay() {
	var cf = confirm("100원 결제 하실래요?");
	if(!cf){
		return;
	}
	var IMP = window.IMP; // 생략 가능
    IMP.init("imp18128138"); // 예: imp00000000
      // IMP.request_pay(param, callback) 결제창 호출
      IMP.request_pay({ // param
          pg: "html5_inicis.INIpayTest",
          pay_method: "card",
          merchant_uid: "ORD"+new Date().getTime(),
          name: $("#goodsName").text(),
          amount: $("#goodsPrice").text(),
          buyer_email: $("#buyerEmail").text(),
          buyer_name: "테스트",
          buyer_tel: "010-7194-4166",
          buyer_addr: "서울특별시 강남구 신사동",
          buyer_postcode: "01181"
      }, function (rsp) { // callback
          if (rsp.success) {
              // 결제 성공 시 로직,
          } else {
              // 결제 실패 시 로직,
          }
      });
    }