package com.xinyue.panshi.classLoader;

/**
 * @author hxy
 * @time 2018/1/20
 * @desc
 */
public class LoaderTest {
    public static void main(String[] args) {
        try {
            /**
             * AppClassLoader: 它负责将系统类路径java -classpath或-Djava.class.path变量所指的目录下的类库加载到内存中
             */
            System.out.println(ClassLoader.getSystemClassLoader());

            /**
             * ExtClassLoader（sun.misc.Launcher$ExtClassLoader）实现的。
             * 它负责将< Java_Runtime_Home >/lib/ext或者由系统变量-Djava.ext.dir指定位置中的类库加载到内存中。
             * 开发者可以直接使用标准扩展类加载器。
             */
            System.out.println(ClassLoader.getSystemClassLoader().getParent());
            /**
             * null
             * Bootstrap 加载 比如java.lang.System 所以自己无法在实现一个System的类，因为它是由Bootstrap加载的根据双亲委派原则，
             * 你加载的时候总是告诉你该类已经加载了。
             * 启动（Bootstrap）类加载器
             */
            System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());


            //查看当前系统类路径中包含的路径条目
            System.out.println(System.getProperty("java.class.path"));
            //调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean
            Class typeLoaded = Class.forName("com.xinyue.panshi.classLoader.LoaderTest");
            //查看被加载的TestBean类型是被那个类加载器加载的
            System.out.println(typeLoaded.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 双亲委派模型的系统实现
    /*protected synchronized Class<?> loadClass(String name,boolean resolve)throws ClassNotFoundException{
        //check the class has been loaded or not
        Class c = findLoadedClass(name);
        if(c == null){
            try{
                // 责任链模式类似，只不过责任链一般是向下传递
                if(parent != null){
                    c = parent.loadClass(name,false);
                }else{
                    c = findBootstrapClassOrNull(name);
                }
            }catch(ClassNotFoundException e){
                //if throws the exception ,the father can not complete the load
            }
            if(c == null){
                c = findClass(name);
            }
        }
        if(resolve){
            resolveClass(c);
        }
        return c;
    }*/

}
