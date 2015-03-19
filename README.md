
play-jade
=========

jade4j wrapper for Play Framwork 1.X


How to Use
-----------

when  
    `app/controllers/Application/index.html` invoked from play controller,  
play-jade check  
    `app/controllers/Application/index.jade`  
if jade file exists, play-jade render html from jade file.  


Example
------
app/views/Application/index.jade

```
doctype html
html
  heaed
    title #{title}
  body
    .main
      h1 #{title}
      table
        tr
          th Java
          td Verbose (FactoryBuilderAdaptorDelegateImpl...)
        tr
          th Python
          td slow
        tr
          th C
          td pointer hell
        tr
          th C++
          td Nobody cares Boost
        tr
          th Scheme
          td cosmic branchkes
        tr
          th Golnag
          td 
            strong 
              u simple! No long compile time! Never bothered me anyway!
```

app/controllers/Application.java

```java
package controllers;

import play.mvc.*;

public class Application extends Controller {
  public static index() {
    String title = "Write in Go!";
    render(title);
  }
}
```


renderd html

``` html
<!DOCTYPE html>
<html>
  <heaed>
    <title>Write in Go!</title>
  </heaed>
  <body>
    <div class="main">
      <h1>Write in Go!</h1>
      <table>
        <tr>
          <th>Java</th>
          <td>Verbose (FactoryBuilderAdaptorDelegateImpl...)</td>
        </tr>
        <tr>
          <th>Python</th>
          <td>slow</td>
        </tr>
        <tr>
          <th>C</th>
          <td>pointer hell</td>
        </tr>
        <tr>
          <th>C++</th>
          <td>Nobody cares Boost</td>
        </tr>
        <tr>
          <th>Scheme</th>
          <td>cosmic branchkes</td>
        </tr>
        <tr>
          <th>Golnag</th>
          <td> 
            <strong> 
              <u>simple! No long compile time! Never bothered me anyway!</u>
            </strong>
          </td>
        </tr>
      </table>
    </div>
  </body>
</html>
```



Install your Play Framwork 1.X Project
-------------------------------

add your dependencies.yml

```
reuqire:
    - play.modules -> play-jade [0.1,)

repositories:
   - play_jade_github_repo:
       type:       http
       artifact:   https://github.com/tomlla/[module]/raw/master/dist/[module]-[revision].zip
       contains:
           - play.modules -> play-jade
```

and run

```
$ play deps
$ play run
```

Links
-----

[Jade について](https://gist.github.com/japboy/5402844)

[jade-lang.com](http://jade-lang.com/reference/)

[jade Github README.md](https://github.com/jadejs/jade/blob/master/README.md)

[jade4j](https://github.com/neuland/jade4j)
