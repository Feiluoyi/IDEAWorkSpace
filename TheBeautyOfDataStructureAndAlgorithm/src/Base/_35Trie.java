package Base;

/**
 * 实现一个Trie树的基本插入和查找功能
 * 删除功能还需要再阅读<<算法>>
 */
public class _35Trie {
    //测试用例
    public static void main(String[] args) {
        _35Trie trie=new _35Trie();
        trie.insert("abcde");
        trie.insert("abczz");
        trie.insert("abc");
        System.out.println(trie.search("a"));
        System.out.println(trie.search("ab"));
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("abcdef"));
        System.out.println(trie.search("ac"));
        System.out.println(trie.search(""));
    }
    //初始化一个特殊字符的根节点
    private TrieNode root=new TrieNode('/');
    //插入节点的方法
    public void insert(String s){
        insert(s.toCharArray());
    }
    private void insert(char[] text){
        TrieNode p=root;
        for (int i = 0; i < text.length; i++) {
            int index=text[i]-'a';
            if(p.children[index]==null) {
                TrieNode newNode=new TrieNode(text[i]);
                p.children[index]=newNode;
            }
            p=p.children[index];
        }
        p.isEnding=true;
    }
    //查找节点的方法
    public boolean search(String s){
       return search(s.toCharArray());
    }
    private boolean search(char[] text){
        TrieNode p=root;
        for (int i = 0; i < text.length; i++) {
            int index=text[i]-'a';
            if(p.children[index]==null) return false;
            p=p.children[index];
        }
        if(p.isEnding==false) return false;
        else return true;
    }
    //节点的类
    class TrieNode{
        public char date;
        public TrieNode[] children=new TrieNode[26];
        boolean isEnding=false;

        public TrieNode(char date) {
            this.date = date;
        }
    }
}
