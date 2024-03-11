# Version Control / Git
There was a time when I would have to argue and push for Git, but it has pretty much become the de facto standard at this point and I haven't
seen anything even come close to attempting to challenge it in recent years.

## Tools
I prefer to use git mostly from the command line with the default tool and the two UIs  `git gui`(for staging and committing) and 
`gitk`(for viewing and search history)

To install git go [here: git-scm.com](https://git-scm.com/)

There are many merge tools, and I've tried a bunch, but I've found that `IntelliJ` works the best, so I use it as my merge tool, which works
well because it's also my IDE. Intellij also has a right-click menu on files that allows you to pull up history or compare with another
branch from history. You can right-click in the left gutter and have IntelliJ to a Git blame inline. You can also select a section of code
and have it find the history for the selection.

## Tutorials and Presentations
TBD

## Commit Standards
All commit messages should have a short title that includes a bug number from whatever bug system you use like Jira
Then there should be an empty line followed by an option body to explain the changes in more detail.

## Branching
I would say it's a good idea to not stay on a feature branch for too long before merging into an integration branch. I've also found
that if you don't stay of a feature branch for too long using rebase on pull is very usable and maintains a cleaner history. If you stay
on a feature branch for too long and you develop merge issues rebase can be a pain.

**Never rebase anything that has already been pushed to an integration repository like GitHub.**

Most places I've worked either followed the fork pr and merge approach or the git flow approach. At this point, I don't see one as 
necessarily that much better than the other, but you should make sure to clean up merged branches on the server, so you don't have
a lot of extra unnecessary branches. Deleting the merge branch on merge is very common.

## Pull Requests
Pull requests are a good place to do code reviews


## Tagging
Every release should be tagged, so it's easy to get back to.

## Git Config
Here is a copy of my git config I have a bunch of aliases to make typing quicker and easier.
```properties
[core]
    filemode = false
    editor = nano
    whitespace = cr-at-eol
    ignorecase = false
[user]
    email = youremail@mail.com
    name = Your Name Here
[pull]
    rebase = true
[color]
    branch = auto
    diff = auto
    interactive = auto
    status = auto
[merge]
    log = true
[alias]
    st = status
    ci = commit
    co = checkout
    br = branch
    ls = log --pretty=format:"%C(yellow)%h%Cred%d\\ %Creset%s%Cblue\\ [%cn]" --decorate
    ll = log --pretty=format:"%C(yellow)%h%Cred%d\\ %Creset%s%Cblue\\ [%cn]" --decorate --numstat
    lu = log -u
    f = "!git ls-files | grep -i"
    cp = cherry-pick
    sl = stash list
    sa = stash apply
    ss = stash save
    sp = stash pop
    s0 = stash show -p stash@{0}
    s1 = stash show -p stash@{1}
    s2 = stash show -p stash@{2}
    s3 = stash show -p stash@{3}
    s4 = stash show -p stash@{4}
    s5 = stash show -p stash@{5}
    la = ! git config --get-regexp ^alias\\. | sed -e s/^alias\\.// -e s/\\ /\\ =\\ /
    rh = reset --hard
    cl = clean -fd
[diff]
    tool = meld
[mergetool "meld"]
    cmd = meld "$LOCAL" "$MERGED" "$REMOTE"
    trustExitCode = false
[guitool "1. stat"]
    cmd = git status
[guitool "4. stash"]
    cmd = git stash
[guitool "5. apply stash"]
    cmd = git stash apply
[guitool "3. stash list"]
    cmd = git stash list
[guitool "2. blame"]
    cmd = git blame $FILENAME
    needsfile = yes
[guitool "6. Revert File"]
    cmd = git checkout $FILENAME
    needsfile = yes
    confirm = yes
[guitool "7. Remove File"]
    cmd = rm $FILENAME
    needsfile = yes
    confirm = yes
[guitool "8. Reset Hard"]
    cmd = git reset --hard
    noconsole = yes
    confirm = yes

[gui]
    spellingdictionary = en_US
    fontui = -family \"DejaVu Sans\" -size 14 -weight normal -slant roman -underline 0 -overstrike 0
    fontdiff = -family \"DejaVu Sans Mono\" -size 12 -weight normal -slant roman -underline 0 -overstrike 0
[guitool "gui blame"]
    cmd = git gui blame $FILENAME
    needsfile = yes
[mergetool]
    keepBackup = false
[guitool "new status"]
    cmd = git st
    confirm = yes
```
