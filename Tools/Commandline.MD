### Commandline tips/tricks

## Aliases
Aliases make your commandline quicker and more efficient, plus they act as documentation of commonly used commands which you can get by typing:
```
alias
```

A list of useful aliases:
```
# some more ls aliases
alias ll='ls -alhF --color=auto'
alias la='ls -Ah --color=auto'
alias l='ls -CFh --color=auto'
alias na='nautilus .'
alias free='free -h'
alias df='df -h'

# some docker aliases
alias d='docker'
alias dst='docker start'
alias dsp='docker stop'
alias dk='docker kill'
alias dl='docker ps -a'
alias dr='docker run'
alias drm='docker rm'
alias ds='docker stats --no-stream'
alias di='docker images'
alias dir='docker image remove'
alias dlog='docker logs -f'

dssh(){
    docker exec -it $1 /bin/bash
}

# k8 aliases
alias k='kubectl'
alias kl='kubectl get nodes'
alias klp='kubectl get pods'
alias kld='kubectl get deployment'
alias klr='kubectl get replicaset'
alias kls='kubectl get service'
alias klst='kubectl get statefulSet'
alias klpv='kubectl get pv'
alias kli='kubectl get ingress'
alias klpc='kubectl get pvc'

alias kdd='kubectl describe deployment'
alias kdi='kubectl describe ingress'
alias kdp='kubectl describe pods'
alias kds='kubectl describe service'

alias krmd='kubectl delete deployment'
alias krms='kubectl delete service'
alias krmp='kubectl delete pv'
alias krmpc='kubectl delete pvc'
alias krmi='kubectl delete ingress'

alias kg='kubectl get'
alias kap='kubectl apply -f'

alias hu='helm uninstall'

hi(){
    helm install -f $1/$2 $1 $1
}

alias code='flatpak run com.visualstudio.code'
```

## bash tricks

* [commandline fu](https://www.commandlinefu.com/commands/browse)
* [awk tutorial](https://youtu.be/FbSpuZVb164?si=rnGW51Z4y3vxtkoE) even though I consider regexes as write only code

### Port Forwarding
```
ssh -L local_port:destination_server_ip:remote_port user_name@ssh_server_hostname
ssh –L 5901:188.17.0.5:4492 pnap@ssh.server.com
```