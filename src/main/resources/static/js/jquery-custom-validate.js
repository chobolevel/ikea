jQuery.validator.addMethod('specialChars', (value, element) => {
    const regex = new RegExp("^[a-zA-Z0-9]+$")
    if(!regex.test(value)) {
        return false
    }
    return true
}, '특수문자는 입력할 수 없습니다.')

jQuery.validator.addMethod('korean', (value, element) => {
    const regex = new RegExp("^[가-힣]+$")
    if(!regex.test(value)) {
        return false
    }
    return true
}, '한글만 입력 가능합니다.')