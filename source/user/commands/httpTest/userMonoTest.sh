echo "[*] 테스트 대상 서비스들이 정상적으로 작동하는지 체크합니다."
http --check-status -v GET http://localhost:8088/api/user/sanityCheck
if [ $? -ne 0 ]; then
    echo "[!] 대상 서비스가 실행되어 있지 않아서 종료됨"
    exit 1
fi



echo "[*] 테스트를 위한 유저를 생성합니다."
http --check-status --json -v PUT http://localhost:8088/api/user/users/signUp email="testemail1@gmail.com" password="testpassword1" name="testname1"
if [ $? -ne 0 ]; then
    echo "[!] 에러 코드가 반환되어서 종료됨"
    exit 1
fi



echo "[*] 로그인을 통해서 JWT 토큰 획득을 시도합니다."
JWT_TOKEN=`http --check-status --json -v PUT http://localhost:8088/api/user/users/signIn email="testemail1@gmail.com" password="testpassword1" | grep "Authorization" | cut -d ' ' -f 2`
AUTH_HEADER=`echo "Bearer $JWT_TOKEN"`
echo "획득한 JWT 토큰 값: $JWT_TOKEN"

echo "[*] JWT 토큰을 이용해서 인증이 제대로 되는지 확인해봅니다."
http --check-status --json -v GET http://localhost:8088/api/user/sanityCheck/authenticationCheck "Authorization:$AUTH_HEADER"
if [ $? -ne 0 ]; then
    echo "[!] 에러 코드가 반환되어서 종료됨"
    exit 1
fi



echo "[*] JWT 토큰을 활용해서 이름을 변경하고, 제대로 변경사항이 적용되는지 확인해봅니다."
http --check-status --json -v PUT http://localhost:8088/api/user/users/updateName name="changedTestName1" "Authorization:$AUTH_HEADER" &&\
http --check-status --json -v GET http://localhost:8082/users
if [ $? -ne 0 ]; then
    echo "[!] 에러 코드가 반환되어서 종료됨"
    exit 1
fi



echo "[*] 모든 테스트를 정상적으로 완료했습니다 !"