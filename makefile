ifeq ($(OS), Windows_NT)
  MAVEN = .\mvnw
  RM = docker rmi -f $(docker images --format "{{.Repository}}:{{.Tag}}"|findstr "waes")
else
  MAVEN = ./mvnw
  RM = docker rmi `docker images | grep waes `
endif

test:
	$(MAVEN) clean test

clean:
	$(MAVEN) clean

package: clean
	$(MAVEN) package

build: clean
	$(MAVEN) package -Dmaven.test.skip=true

docker: build
	@docker-compose up --build

remove:
	@docker-compose down
	@$(RM)