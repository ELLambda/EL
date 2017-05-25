package Story;

/**
 * 类说明   把命令与UI对接
 *
 * @author Andy
 */

public class CommandManager {
    public FileManager filemanager;

    public CommandManager(int index) {
        filemanager = new FileManager(index);

    }


}
