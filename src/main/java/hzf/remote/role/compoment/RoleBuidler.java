package hzf.remote.role.compoment;

import hzf.remote.base.Identity;
import hzf.remote.data.BrainData;
import hzf.remote.role.base.RoleDoSomeThing;

/**
 * Created by WTO on 2016/5/1 0001.
 *
 */
public class RoleBuidler
{
    public static RoleDoSomeThing createRole(BrainData brain)
    {
        RoleDoSomeThing role = new IMSomeOne(brain);
//        switch (identity)
//        {
//            case Mediator:
//                //
//                role = new IMMediator(identity);
//                break;
//            case Leader:
//                //
//                role = new IMLeader(identity);
//                break;
//            case Worker:
//            case NoBody:
//                //
//                role = new IMWorker(identity);
//                break;
//        }
        return role;
    }
}
