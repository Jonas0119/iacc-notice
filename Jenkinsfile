//gitlab的凭证 
def git_auth = "3de8a599-6746-4e50-a6d4-386c9a448622"
//构建版本的名称 
//def tag = "latest" 
def VN = VersionNumber(projectStartDate: '2022-07-01', versionNumberString: '${BUILDS_ALL_TIME}', versionPrefix: 'v1.0.', worstResultForIncrement: 'SUCCESS')
//Harbor私服地址 
def harbor_url = "harbor01.data-tech.com.cn"
//Harbor的项目名称
def harbor_project_name = "${active}-projects"
//Harbor的凭证
def harbor_auth = "8bb86d97-9726-4589-ae92-210efa00394b"
//定义label
def label = "jenkins-slave"

podTemplate(inheritFrom: 'jnlp', instanceCap: 0, label: 'jenkins-slave', name: '', namespace: 'kube-jenkins', nodeSelector: '', podRetention: always(), serviceAccount: '', yaml: '')
{
node(label) {
	stage('拉取代码') {
	    container('jnlp'){
	        checkout([$class: 'GitSCM', branches: [[name: '${branch}']], extensions: [], userRemoteConfigs: [[credentialsId: '3de8a599-6746-4e50-a6d4-386c9a448622', url: 'http://172.19.100.66:8082/tjfae-cloud/iacc-notice.git']]])
	    }
	}
	
	stage('编译，构建镜像,并上传至harbor仓库') {
		container('jnlp'){
	        //定义镜像名称
	        def imageName = "${project_name}:${VN}"
	        //编译，安装公共工程
	        //sh "mvn -f usercenter-api clean install"
	        //编译，构建本地镜像
	        sh "mvn -f ${project_name} clean package -P ${active} -Dmaven.test.skip=true dockerfile:build -Ddockerfile.repository=${project_name} -Ddockerfile.tag=${VN}"
	        //给镜像打标签
	        sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
	        //登录Harbor，并上传镜像
	        withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) { 
	            //登录
	            sh "docker login -u ${username} -p ${password} ${harbor_url}"
	            //上传镜像
	            sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}" }
	        //删除本地镜像' 
	        sh "docker rmi -f ${imageName}"
	        sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"
	    }
	}
	
	stage('发布至K8S') {
	    container('jnlp'){
            checkout([$class: 'GitSCM', branches: [[name: 'master']], extensions: [], userRemoteConfigs: [[credentialsId: '3de8a599-6746-4e50-a6d4-386c9a448622', url: 'http://172.19.100.66:8082/tjfae-cloud/k8s-yaml.git']]])
		    sh """
			sed -i 's#HARBOR_IMAGE_TAG#${VN}#' yaml-${active}/${project_name}-deployment.yaml
			kubectl apply -f yaml-${active}/${project_name}-deployment.yaml
			"""
		}
	}
	
}
}