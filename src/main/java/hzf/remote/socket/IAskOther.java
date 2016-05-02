package hzf.remote.socket;

import hzf.remote.role.base.RoleDoSomeThing;
import hzf.remote.role.beans.Question;
import hzf.remote.utils.socket.CloseSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class IAskOther
{
    private RoleDoSomeThing role;
    public IAskOther(RoleDoSomeThing role)
    {
        this.role = role;
    }

    public void go(List<Question> questions)
    {
        new Thread(new SayToAnother(questions)).start();
    }

    class SayToAnother implements Runnable
    {
        private List<Question> questions;
        public SayToAnother(List<Question> questions)
        {
            this.questions = questions;
        }

        public void run()
        {
            for (Question question : questions)
            {
                Socket other = null;
                try
                {
                    String address = question.getIP();
                    Integer port = question.getPort();
                    String content = question.getContent();

                    other = new Socket(address, port);
                    BufferedReader in = new BufferedReader(new InputStreamReader(other.getInputStream()));
                    PrintWriter out = new PrintWriter(other.getOutputStream());

                    while (true)
                    {
                        // 问别人问题
                        out.println(content);
                        out.flush();

                        // 收到答复
                        String reply = in.readLine();
                        // 处理答复，有可能继续提问
                        System.out.println(reply);
                        if ("receive".equals(reply) || "end".equals(reply))
                        {
                            break;
                        }
                        content = role.go(reply);
                    }
                    System.out.println("当前问答结束，" + other.getInetAddress().getHostAddress() + ":" + other.getPort());
                }
                catch (Exception e) { e.printStackTrace(); }
                finally {
                    CloseSocket.go(other);
                }
            }
        }
    }
}
