<template>
    <div class="container mt-2" style="" id="qnaWriteDiv">
        <div class="container mt-3">
            <div class="row justify-content-md-center">
                <div class="col-md-10">
                    <table class="table table-condensed">
                        <thead>
                            <tr align="center">
                                <th width="10%" class="align-middle">제목</th>
                                <th width="60%"><textarea class="form-control" rows="1" @input="changeTitleText" maxlength="50" :value="titleText" required style="width:100%; resize: none;" placeholder="제목 입력..."></textarea></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2">
                                    <p><textarea class="form-control" id="commentTextArea" rows="5" style="resize: none;" maxlength="500" :value="contentText" @input="changeContent" required placeholder="내용 입력...."></textarea></p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <button class="btn btn-outline-secondary mt-2" style="float:right;" @click="submitCreate">등록</button>
                        <button class="btn btn-outline-secondary mt-2" style="" @click="returnList">등록 취소</button>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'QnaWrite',

    data() {
        return {
            contentText:'',
            titleText:'',
        };
    },

    computed: {
        rows() {
                return this.totalElements;
            }
    },

    mounted() {
    },

    watch: {
    },

    methods: {
        changeTitleText(e) {
            this.titleText = e.target.value;
        },
        changeContent(e) {
            this.contentText = e.target.value;
        },
        returnList() {
            this.$router.replace({name:'qnalist', params:{text:'QnA'}});
        },
        submitCreate() {
            let url = '/boards';
            http.post(url,{"title" : this.titleText, "content" : this.contentText,"boardType" : "qna"})
            .then(() =>{
                alert('등록 성공!');
                this.returnList();
            })
            .catch((error) => {
                console.log(error);
                this.$router.replace({name:'qnalist', params:{text:'QnA'}});
                alert('등록 실패!!');
            })
        },
    },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');

.commentTXT {
    font-weight: bolder;
}
.commentTable {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 20px;
    color:black;
    transform: skew(-0.1deg);
}
</style>