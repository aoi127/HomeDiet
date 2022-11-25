<template>
  <view>
    <uni-section title="性别" type="line">
      <uni-data-select
        v-model="sex"
        :localdata="sex_range"
        @change="change"
      ></uni-data-select>
    </uni-section>
	
	<uni-section title="体重" type="line" padding>
		<uni-easyinput errorMessage v-model="weight" focus placeholder="请输入内容" @input="input"></uni-easyinput>
	</uni-section>


	<uni-section title="身高" type="line" padding>
		<uni-easyinput errorMessage v-model="height" focus placeholder="请输入内容" @input="input"></uni-easyinput>
	</uni-section>



	<uni-section title="年龄" type="line" padding>
		<uni-easyinput errorMessage v-model="age" focus placeholder="请输入内容" @input="input"></uni-easyinput>
	</uni-section>


	<uni-section title="体脂率" type="line" padding>
		<uni-easyinput errorMessage v-model="bfr" focus placeholder="请输入内容" @input="input"></uni-easyinput>
	</uni-section>
	
	
	<uni-section title="运动系数" type="line">
	  <uni-data-select
	    v-model="tdee"
	    :localdata="tdee_range"
	    @change="change"
	  ></uni-data-select>
	</uni-section>
	
  </view>
</template>

<script>
  export default {
  	data() {
  		return {
			value: '',
			password: '',
			placeholderStyle: "color:#666;font-size:14px",
			styles: {
				color: '#666',
				borderColor: '#666'
			},
			
			
			
			
			
			
  			sex: 0,
  			sex_range: [
  				{ sex: 0, text: "男"},
  				{ sex: 1, text: "女"},
  			],
  			
  			tdee: 0,
  			tdee_range: [
  				{ tdee: 0, text: "几乎不运动，常坐办公室"},
  				{ tdee: 1, text: "每天走走路，或者每周轻运动1-3次"},
  				{ tdee: 2, text: "每天在外面跑，或者中等强度运动每周3-5天"},
  				{ tdee: 3, text: "很活跃，体力劳动者，或者每周运动6-7次"},
  				{ tdee: 4, text: "运动员，教练等每天大强度体力劳动者"},
  			],
  			
  			
  		};
  	},
  	
  	
  	onLoad() {},
  	components: {},
  	methods: {
  		clickButton: function(){
  			uni.navigateTo({
  				url : 'today_y'
  			})
  		},
  		
  		change(e){
  			console.log("e:", e);
  		},
  		
  	},
  };
</script>

<style lang="scss">
  .text {
    font-size: 12px;
    color: #666;
    margin-top: 5px;
  }

  .uni-px-5 {
    padding-left: 10px;
    padding-right: 10px;
  }

  .uni-pb-5 {
    padding-bottom: 10px;
  }
  
  .uni-mt-5 {
  	margin-top: 5px;
  }
  
  
</style>